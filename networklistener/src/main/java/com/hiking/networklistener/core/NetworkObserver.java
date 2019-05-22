package com.hiking.networklistener.core;

import com.hiking.networklistener.annotation.NetType;
import com.hiking.networklistener.bean.MethodManager;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 统一网络变化观察者
 */
public class NetworkObserver {
    ObserverManager mObserverManager;

    public NetworkObserver(ObserverManager observerManager) {
        mObserverManager = observerManager;
    }

    public void post(@NetType String netType) {
        Map<Object, List<MethodManager>> networkList = mObserverManager.getNetworkList();
        Set<Object> observers = networkList.keySet();
        for (Object observer : observers) {
            List<MethodManager> methodManagers = networkList.get(observer);
            for (MethodManager methodManager : methodManagers) {
                switch (methodManager.getNetType()) {
                    case NetType.AUTO:
                        invoke(methodManager, observer, netType);
                        break;
                    case NetType.CMNET:
                        if (netType == NetType.CMNET || netType == NetType.NONE) {
                            invoke(methodManager, observer, netType);
                        }
                        break;
                    case NetType.CMWAP:
                        if (netType == NetType.CMWAP || netType == NetType.NONE) {
                            invoke(methodManager, observer, netType);
                        }
                        break;
                    case NetType.WIFI:
                        if (netType == NetType.WIFI || netType == NetType.NONE) {
                            invoke(methodManager, observer, netType);
                        }
                        break;
                    case NetType.NONE:
                        invoke(methodManager, observer, netType);
                        break;

                }
            }
        }
    }

    private void invoke(MethodManager manager, Object o, String netType) {
        Method executeMethod = manager.getMethod();
        try {
            executeMethod.invoke(o, netType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
