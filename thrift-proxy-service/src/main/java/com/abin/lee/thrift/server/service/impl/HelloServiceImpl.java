package com.abin.lee.thrift.server.service.impl;

import com.abin.lee.thrift.api.HelloService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

/**
 * Created by abin on 2016/12/13 14:44.
 * thrift-svr
 * com.abin.lee.thrift.server.service.impl
 */
@Service
public class HelloServiceImpl implements HelloService.Iface{

    @Override
    public String sayHello(String username) throws TException {
        String result = "sayHello-hello-"+username;
        System.out.println("sayHello-result="+result);
        return result;
    }




}
