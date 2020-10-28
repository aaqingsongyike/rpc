package com.example.rpc.util.proxy;import com.example.rpc.util.hystrix.HystrixUtils;import org.aopalliance.intercept.MethodInterceptor;import org.aopalliance.intercept.MethodInvocation;import org.springframework.util.StringUtils;public class RPCProxyFactory implements MethodInterceptor {    private String proxyMethod;    private String domain;    public RPCProxyFactory(String proxyMethod, String domain) {        this.proxyMethod = proxyMethod;        this.domain = domain;    }    @Override    public Object invoke(MethodInvocation invocation) throws Throwable {        Object rpcProxy = getRPCProxy(proxyMethod, invocation);        return rpcProxy;    }    public Object getRPCProxy(String proxyMethod, MethodInvocation invocation) throws Throwable {        if (StringUtils.pathEquals("HTTP", proxyMethod)) {            RPCClient httpClient = new HttpClientRegisterPool(domain);            HystrixUtils hystrixUtils = new HystrixUtils(domain);            hystrixUtils.setInvocation(invocation);            hystrixUtils.setRpcClient(httpClient);            String execute = hystrixUtils.execute();            System.out.println("execute={" + execute + "}");//            Object rpcClient = httpClient.getRPCClient(invocation);            return execute;        }        return null;    }    public String getProxyMethod() {        return proxyMethod;    }    public void setProxyMethod(String proxyMethod) {        this.proxyMethod = proxyMethod;    }    public String getDomain() {        return domain;    }    public void setDomain(String domain) {        this.domain = domain;    }}