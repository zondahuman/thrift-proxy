package com.abin.lee.thrift.common.rpc.factory;


import com.abin.lee.thrift.common.rpc.service.ThriftServerAddressRegister;
import com.abin.lee.thrift.common.rpc.service.ThriftServerIpResolve;

/**
 * 服务端注册服务工厂
 */
public class ThriftServiceServerFactory {
	// 优先级
	private Integer weight = 1;// default
	// 服务实现类
	private Object service;// serice实现类
	//服务版本号
	private String version;
	// 解析本机IP
	private ThriftServerIpResolve thriftServerIpResolve;
	//服务注册
	private ThriftServerAddressRegister thriftServerAddressRegister;


	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public void setService(Object service) {
		this.service = service;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setThriftServerIpResolve(ThriftServerIpResolve thriftServerIpResolve) {
		this.thriftServerIpResolve = thriftServerIpResolve;
	}

	public void setThriftServerAddressRegister(ThriftServerAddressRegister thriftServerAddressRegister) {
		this.thriftServerAddressRegister = thriftServerAddressRegister;
	}


	public Integer getWeight() {
		return weight;
	}

	public Object getService() {
		return service;
	}

	public String getVersion() {
		return version;
	}

	public ThriftServerIpResolve getThriftServerIpResolve() {
		return thriftServerIpResolve;
	}

	public ThriftServerAddressRegister getThriftServerAddressRegister() {
		return thriftServerAddressRegister;
	}
}
