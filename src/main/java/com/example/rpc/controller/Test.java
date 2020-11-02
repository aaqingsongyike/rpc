package com.example.rpc.controller;import com.example.rpc.service.StudentService;import com.example.rpc.util.reference.RPCReferenceBean;import org.apache.commons.lang.math.RandomUtils;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.beans.factory.annotation.Qualifier;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.ResponseBody;import java.util.HashMap;import java.util.Map;@Controllerpublic class Test {    @Autowired    @Qualifier("StudentService")    private RPCReferenceBean rpcReferenceBean;    @ResponseBody    @RequestMapping(value = "test", method = RequestMethod.POST)    public String test() throws Exception {        int i = RandomUtils.nextInt(30) + 1;        Map<String, String> map = new HashMap<>();        map.put(String.valueOf(i), "v" + i);        StudentService object = (StudentService) rpcReferenceBean.getObject();        String s = object.sayHello(map);        System.out.println("最后的结果：" + s);        return s;    }}