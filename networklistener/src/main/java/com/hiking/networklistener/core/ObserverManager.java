package com.hiking.networklistener.core;

import android.util.Log;

import com.hiking.networklistener.annotation.Network;
import com.hiking.networklistener.bean.Constants;
import com.hiking.networklistener.bean.MethodManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网络变化观察者注册工具
 */
public class ObserverManager {

    private Map<Object, List<MethodManager>> mNetworkList = new HashMap<>();

    public Map<Object, List<MethodManager>> getNetworkList() {
        return mNetworkList;
    }

    public void registerObserver(Object observer) {
        List<MethodManager> methodManagers = mNetworkList.get(observer);
        if (methodManagers == null) {
            methodManagers = findAnnotationMethod(observer);
            mNetworkList.put(observer, methodManagers);
        }
    }

    public void unregisterObserver(Object observer) {
        if (!mNetworkList.isEmpty()) {//说明有广播被注册过
            mNetworkList.remove(observer);
        }
    }

    private List<MethodManager> findAnnotationMethod(Object observer) {
        List<MethodManager> methodManagers = new ArrayList<>();
        Class<?> clazz = observer.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            //获取含NetworkObserver的注解
            Network annotation = m.getAnnotation(Network.class);
            if (annotation == null) {
                continue;
            }
            m.setAccessible(true);
            //判断返回类型
            /*Type genericReturnType = m.getGenericReturnType();
            if(!"void".equals(genericReturnType.toString())){
                throw new RuntimeException(m.getName()+"返回类型必须是void");
            }*/
            // 参数校验
            Class<?>[] parameterTypes = m.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new RuntimeException(String.format("返回参数必须是一个，当前方法%s:%s的返回参数长度是%s", new Object[]{clazz.getSimpleName(), m.getName(), parameterTypes.length}));
            }
            MethodManager methodManager = new MethodManager(parameterTypes[0], annotation.type(), m);
            methodManagers.add(methodManager);

        }
        return methodManagers;
    }
}
