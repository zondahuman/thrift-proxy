package com.abin.lee.thrift.client.controller;

import com.abin.lee.thrift.api.HelloService;
import com.abin.lee.thrift.api.UserService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by abin on 2016/12/14 2016/12/14.
 * thrift-proxy
 * com.abin.lee.thrift.client.controller
 */
@Controller
public class ThriftClientController {

    @Resource
    HelloService.Iface helloService;
    @Resource
    UserService.Iface userService;

    /**
     *
     * http://localhost:7200/hello
     * http://localhost:8200/hello
     * http://172.16.2.145:9600/hello
     *
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() throws TException {
        String result = helloService.sayHello("lee");
        return result;
    }

    /**
     *
     * http://localhost:7200/user
     * http://localhost:8200/user
     *
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String user() throws TException {
        String result = userService.sayUser("harry");
        return result;
    }



}
