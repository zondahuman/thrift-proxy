package com.abin.lee.thrift.common.rpc.load;

import com.abin.lee.thrift.common.context.SpringContextUtils;
import com.abin.lee.thrift.common.rpc.exception.ThriftException;
import com.abin.lee.thrift.common.rpc.factory.ThriftServiceServerFactory;
import com.abin.lee.thrift.common.rpc.service.ThriftServerAddressRegister;
import com.abin.lee.thrift.common.rpc.service.ThriftServerIpResolve;
import com.google.common.collect.Maps;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Closeable;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;

/**
 * 服务端注册服务工厂
 */
@Component
public class ThriftServiceServerStartUp implements InitializingBean,Closeable,Ordered  {

//    private ThriftServiceServerFactory thriftServiceServerFactory;
    //服务注册
    @Resource
ThriftServerAddressRegister thriftServerAddressRegister;
    private ServerThread serverThread;
    private Integer port=9000;
    //    private final TMultiplexedProcessor processor = new TMultiplexedProcessor();
    // 解析本机IP
    @Resource
    ThriftServerIpResolve thriftServerIpResolve;
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, TProcessor> processorMap = Maps.newConcurrentMap();
        Map<String, String> registerMap = Maps.newConcurrentMap();
        Map<String, ThriftServiceServerFactory> handlers = SpringContextUtils.getBeansOfType(ThriftServiceServerFactory.class);
        for(Iterator<Map.Entry<String, ThriftServiceServerFactory>> iterator=handlers.entrySet().iterator();iterator.hasNext();){
            Map.Entry<String, ThriftServiceServerFactory> entry = iterator.next();
            String beanName = entry.getKey();
            ThriftServiceServerFactory instance = entry.getValue();
            String serverIP = thriftServerIpResolve.getServerIp();
            if (StringUtils.isEmpty(serverIP)) {
                throw new ThriftException("cant find server ip...");
            }
            Integer weight = instance.getWeight();
            String version = instance.getVersion();
            String hostname = serverIP + ":" + port + ":" + weight;
            Object service = instance.getService();
            Class<?> serviceClass = service.getClass();
            // 获取实现类接口
            Class<?>[] interfaces = serviceClass.getInterfaces();
            if (interfaces.length == 0) {
                throw new IllegalClassFormatException("service-class should implements Iface");
            }
            // reflect,load "Processor";
            TProcessor processor = null;
            String serviceName = null;
            for (Class<?> clazz : interfaces) {
                String cname = clazz.getSimpleName();
                if (!cname.equals("Iface")) {
                    continue;
                }
                serviceName = clazz.getEnclosingClass().getName();
                String pname = serviceName + "$Processor";
                try {
                    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                    Class<?> pclass = classLoader.loadClass(pname);
                    if (!TProcessor.class.isAssignableFrom(pclass)) {
                        continue;
                    }
                    Constructor<?> constructor = pclass.getConstructor(clazz);
                    processor = (TProcessor) constructor.newInstance(service);
                    break;
                } catch (Exception e) {
                    //
                }
            }
            if (processor == null) {
                throw new IllegalClassFormatException("service-class should implements Iface");
            }
            processorMap.put(serviceName,processor);
            registerMap.put(serviceName, version + "|" + hostname);
        }

        //需要单独的线程,因为serve方法是阻塞的.
        serverThread = new ServerThread(processorMap, port);
        serverThread.start();
        // 注册服务
        if (thriftServerAddressRegister != null) {
            thriftServerAddressRegister.register(registerMap);
        }

    }


    class ServerThread extends Thread {
        private TServer server;
        ServerThread(Map<String, TProcessor> processorMap, int port) throws Exception {
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(port);
            TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(serverTransport);
            TMultiplexedProcessor processor = new TMultiplexedProcessor();

            for(Iterator<Map.Entry<String, TProcessor>> iterator=processorMap.entrySet().iterator();iterator.hasNext();) {
                Map.Entry<String, TProcessor> entry = iterator.next();
                String beanName = entry.getKey();
                TProcessor instance = entry.getValue();
                processor.registerProcessor(beanName, instance);
            }
            TProcessorFactory processorFactory = new TProcessorFactory(processor);
            tArgs.processorFactory(processorFactory);
            tArgs.transportFactory(new TFramedTransport.Factory());
            tArgs.protocolFactory( new TBinaryProtocol.Factory(true, true));
            server = new TThreadedSelectorServer(tArgs);
        }

        @Override
        public void run(){
            try{
                //启动服务
                server.serve();
            }catch(Exception e){
                //
            }
        }

        public void stopServer(){
            server.stop();
        }
    }

    public void close() {
        serverThread.stopServer();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
