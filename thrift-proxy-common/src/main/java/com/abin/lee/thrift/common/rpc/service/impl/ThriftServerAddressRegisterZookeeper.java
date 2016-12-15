package com.abin.lee.thrift.common.rpc.service.impl;

import com.abin.lee.thrift.common.rpc.exception.ThriftException;
import com.abin.lee.thrift.common.rpc.service.ThriftServerAddressRegister;
import com.google.common.base.Splitter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  注册服务列表到Zookeeper
 */
public class ThriftServerAddressRegisterZookeeper implements ThriftServerAddressRegister,Closeable{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private CuratorFramework zkClient;
	
	public ThriftServerAddressRegisterZookeeper(){}
	
	public ThriftServerAddressRegisterZookeeper(CuratorFramework zkClient){
		this.zkClient = zkClient;
	}

	public void setZkClient(CuratorFramework zkClient) {
		this.zkClient = zkClient;
	}

	@Override
	public void register(Map<String, String> registerMap) {
		if(zkClient.getState() == CuratorFrameworkState.LATENT){
			zkClient.start();
		}

		//临时节点
		try {
			for(Iterator<Map.Entry<String, String>> iterator = registerMap.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry<String, String> entry = iterator.next();
				String service = entry.getKey();
				String params = entry.getValue();
				List<String> instance = Splitter.on("|").splitToList(entry.getValue());
				String version = instance.get(0);
				if(StringUtils.isEmpty(version)){
					version="1.0.0";
				}
				String address = instance.get(1);
				zkClient.create()
						.creatingParentsIfNeeded()
						.withMode(CreateMode.EPHEMERAL)
						.forPath("/" + service + "/" + version + "/" + address);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("register service address to zookeeper exception:{}",e);
			throw new ThriftException("register service address to zookeeper exception: address UnsupportedEncodingException", e);
		} catch (Exception e) {
			logger.error("register service address to zookeeper exception:{}",e);
			throw new ThriftException("register service address to zookeeper exception:{}", e);
		}
	}
	
	public void close(){
		zkClient.close();
	}
}
