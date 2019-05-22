package com.hiking.networklistener;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import com.hiking.networklistener.core.NetworkBroadcastReceiver;
import com.hiking.networklistener.core.NetworkCallbackImpl;
import com.hiking.networklistener.core.NetworkObserver;
import com.hiking.networklistener.core.ObserverManager;

/**
 * 对外网络处理工具
 */
public class NetworkManager {
    public static final String TAG = NetworkManager.class.getSimpleName();
    private Application mApplication;
    private NetworkObserver mNetworkObserver;
    private ObserverManager mObserverManager;


    private static class NetworkManagerHolder {
        private static final NetworkManager mNetworkManager = new NetworkManager();
    }

    private NetworkManager() {
        mObserverManager = new ObserverManager();
        mNetworkObserver = new NetworkObserver(mObserverManager);
    }

    public static NetworkManager getIns() {
        return NetworkManagerHolder.mNetworkManager;
    }

    public Application getApplication() {
        if (mApplication == null) {
            Log.e(TAG, "请初始化代码");
            new IllegalArgumentException("请初始化代码  NetworkManager:init()");
        }
        return mApplication;
    }

    /**
     * 初始化网络监听
     *
     * @param application
     */
    public void init(Application application) {
        mApplication = application;
        registerObserved();
    }

    private void registerObserved() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCallbackImpl.register(mApplication, mNetworkObserver);
        } else {
            NetworkBroadcastReceiver.register(mApplication, mNetworkObserver);
        }
    }

    public void unregisterObserved() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCallbackImpl.unregister(mApplication);
        } else {
            NetworkBroadcastReceiver.unregister(mApplication);
        }
        mObserverManager.getNetworkList().clear();
    }

    public void registerObserver(Object observer) {
        mObserverManager.registerObserver(observer);
    }

    public void unregisterObserver(Object observer) {
        mObserverManager.unregisterObserver(observer);
    }

}
