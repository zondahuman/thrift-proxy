package com.abin.lee.thrift.server.service.impl;

import com.abin.lee.thrift.api.UserService;
import com.abin.lee.thrift.common.util.ByteBufferUtil;
import com.google.common.collect.Interner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by abin on 2016/12/13 14:44.
 * thrift-svr
 * com.abin.lee.thrift.server.service.impl
 */
@Service
public class UserServiceImpl implements UserService.Iface {


    @Override
    public String sayUser(String userName) throws TException {
        String result = "sayUser-hello-"+userName;
        System.out.println("sayUser-result="+result);
        return result;
    }

    @Override
    public List<Integer> find(String userName) throws TException {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        return list;
    }

    @Override
    public List<String> findUser(int age) throws TException {
        List<String> list = Lists.newArrayList();
        list.add("abin1");
        list.add("abin2");
        return list;
    }

    @Override
    public Map<Integer, String> findUser1(int age) throws TException {
        Map<Integer, String> response = Maps.newHashMap();
        response.put(1, "abin1");
        response.put(2, "abin2");
        return response;
    }

    @Override
    public Map<String, String> findUser2(int age) throws TException {
        Map<String, String> response = Maps.newHashMap();
        response.put("lee1", "abin1");
        response.put("lee2", "abin2");
        return response;
    }

    @Override
    public Map<String, List<Integer>> findUser3(int age) throws TException {
        Map<String, List<Integer>> response = Maps.newHashMap();
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        response.put("lee", list);
        return response;
    }

    @Override
    public Set<List<Integer>> findUser4(ByteBuffer id) throws TException {
        Set<List<Integer>> response = Sets.newHashSet();
        List<Integer> list = Lists.newArrayList();
        Integer param = Ints.tryParse(ByteBufferUtil.getString(id));
        list.add(param);
        list.add(888);
        response.add(list);
        return response;
    }

    @Override
    public ByteBuffer findUser5(int id) throws TException {
        String result = "findUser5--hello" + id;
        return ByteBufferUtil.getByteBuffer(result);
    }
}
