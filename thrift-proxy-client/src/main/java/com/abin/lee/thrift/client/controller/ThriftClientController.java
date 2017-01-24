package com.abin.lee.thrift.client.controller;

import com.abin.lee.thrift.api.HelloService;
import com.abin.lee.thrift.api.UserService;
import com.abin.lee.thrift.client.util.ByteBufferUtil;
import com.abin.lee.thrift.model.HelloInfo;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @RequestMapping(value = "/sayHello1", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello1(String param) throws TException {
        String result = helloService.sayHello(param);
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
     * http://localhost:7200/sayUser
     * http://localhost:8200/sayUser
     *
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/sayUser", method = RequestMethod.GET)
    @ResponseBody
    public String sayUser() throws TException {
        String result = userService.sayUser("harry");
        return result;
    }


    /**
     * http://localhost:7200/find
     *
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> find() throws TException {
        List<Integer> result = userService.find("harry");
        return result;
    }


    /**
     *  http://localhost:7200/findUser
     *
     *
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ResponseBody
    public List<String> findUser() throws TException {
        List<String> result = userService.findUser(1);
        return result;
    }


    @RequestMapping(value = "/findUser1", method = RequestMethod.GET)
    @ResponseBody
    public Map<Integer,String> findUser1() throws TException {
        Map<Integer,String> result = userService.findUser1(1);
        return result;
    }

    /**
     *
     * http://localhost:7200/findUser2
     *
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/findUser2", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> findUser2() throws TException {
        Map<String,String> result = userService.findUser2(1);
        return result;
    }

    /**
     *
     *  http://localhost:7200/findUser3
     *
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/findUser3", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,List<Integer>> findUser3() throws TException {
        Map<String,List<Integer>> result = userService.findUser3(1);
        return result;
    }

    /**
     *
     * http://localhost:7200/findUser4
     *
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/findUser4", method = RequestMethod.GET)
    @ResponseBody
    public Set<List<Integer>> findUser4() throws TException {
        String param = "1986";
        Set<List<Integer>> result = userService.findUser4(ByteBufferUtil.getByteBuffer(param));
        return result;
    }

    /**
     *
     * http://localhost:7200/findUser5
     *
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/findUser5", method = RequestMethod.GET)
    @ResponseBody
    public String findUser5() throws TException {
        Integer param = 1;
        ByteBuffer byteBuffer = userService.findUser5(1);
        String result = ByteBufferUtil.getString(byteBuffer);
        return result;
    }




}
