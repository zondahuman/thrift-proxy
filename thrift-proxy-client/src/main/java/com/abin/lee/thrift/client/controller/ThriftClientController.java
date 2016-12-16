package com.abin.lee.thrift.client.controller;

import com.abin.lee.thrift.api.HelloService;
import com.abin.lee.thrift.api.UserService;
import com.abin.lee.thrift.model.HelloInfo;
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
     * http://localhost:7200/sayHello
     * http://localhost:8200/sayHello
     * http://172.16.2.145:9600/sayHello
     *
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello() throws TException {
        String result = helloService.sayHello("lee");
        return result;
    }

    /**
     *
     * http://localhost:7200/findHelloInfo
     * http://localhost:8200/findHelloInfo
     * http://172.16.2.145:9600/findHelloInfo
     *
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/findHelloInfo", method = RequestMethod.GET)
    @ResponseBody
    public HelloInfo findHelloInfo() throws TException {
        Integer id =1;
        HelloInfo result = helloService.findHelloInfo(id);
        return result;
    }

    /**
     *
     * http://localhost:7200/apply
     * http://localhost:8200/apply
     * http://172.16.2.145:9600/apply
     *
     * @throws TException
     */
    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    @ResponseBody
    public void apply() throws TException {
        String userName = "abin1";
        int threshold = 100;
        helloService.apply(userName, threshold);
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
