package com.abin.lee.thrift.server.service.impl;

import com.abin.lee.thrift.api.UserService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

/**
 * Created by abin on 2016/12/13 14:44.
 * thrift-svr
 * com.abin.lee.thrift.server.service.impl
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    public String find(Integer id){
        return "UserService-"+id;
    }

    @Override
    public String sayUser(String username) throws TException {
        String result = "sayUser-hello-"+username;
        System.out.println("sayUser-result="+result);
        return result;
    }

}
