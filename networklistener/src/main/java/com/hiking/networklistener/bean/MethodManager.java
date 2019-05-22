package com.hiking.networklistener.bean;

import com.hiking.networklistener.annotation.NetType;

import java.lang.reflect.Method;

public class MethodManager {
    //参数类型
    private Class<?> type;

    //网络类型
    private @NetType
    String netType;

    //网络状态监听方法
    private Method method;

    public MethodManager(Class<?> type, String netType, Method method) {
        this.type = type;
        this.netType = netType;
        this.method = method;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
