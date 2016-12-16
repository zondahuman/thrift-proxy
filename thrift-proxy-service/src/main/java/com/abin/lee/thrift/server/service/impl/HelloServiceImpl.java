package com.abin.lee.thrift.server.service.impl;

import com.abin.lee.thrift.api.HelloService;
import com.abin.lee.thrift.enums.OperateType;
import com.abin.lee.thrift.exception.HelloException;
import com.abin.lee.thrift.model.HelloInfo;
import com.google.common.collect.ImmutableMap;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by abin on 2016/12/13 14:44.
 * thrift-svr
 * com.abin.lee.thrift.server.service.impl
 */
@Service
public class HelloServiceImpl implements HelloService.Iface {

    @Override
    public String sayHello(String username) throws TException {
        String result = "sayHello-hello-" + username;
        System.out.println("sayHello-result=" + result);
        return result;
    }

    @Override
    public HelloInfo findHelloInfo(int id) throws HelloException, TException {
        System.out.println("sayHello-id=" + id);
        HelloInfo helloInfo = new HelloInfo();
        helloInfo.setId(id);
        helloInfo.setGreetings("Conguratulation to you");
        helloInfo.setOperateType(OperateType.FIND);
//        Map<Integer, String> builder = new ImmutableMap.Builder<Integer, String>().put(1, "lee1").put(2, "lee2").build();
        ImmutableMap.Builder<Integer, String> builder = ImmutableMap.builder();
        builder.put(1, "lee1");
        builder.put(2, "lee2");
        helloInfo.setWords(builder.build());
        return helloInfo;
    }

    @Override
    public void apply(String userName, int threshold) throws HelloException, TException {
        System.out.println("apply-userName=" + userName + ", threshold=" + threshold);
    }
}
