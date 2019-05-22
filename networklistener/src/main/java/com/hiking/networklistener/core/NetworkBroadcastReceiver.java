package com.hiking.networklistener.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.hiking.networklistener.bean.Constants;
import com.hiking.networklistener.util.NetworkUtil;

/**
 * 广播监听网络变化广播
 */
public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private static NetworkObserver mNetworkObserver;
    private static NetworkBroadcastReceiver mNetworkBroadcastReceiver;


    public static void register(Context c, NetworkObserver observer) {
        mNetworkObserver = observer;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ANDROID_NET_CHANGE_ACTION);
        if (mNetworkBroadcastReceiver == null) {
            mNetworkBroadcastReceiver = new NetworkBroadcastReceiver();
        }
        c.registerReceiver(mNetworkBroadcastReceiver, intentFilter);
    }

    public static void unregister(Context c) {
        c.unregisterReceiver(mNetworkBroadcastReceiver);
        mNetworkBroadcastReceiver = null;
        mNetworkObserver = null;
    }

    @Override

    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            Log.e(Constants.TAG, "广播异常了");
            return;
        }
        if (intent.getAction().equalsIgnoreCase(Constants.ANDROID_NET_CHANGE_ACTION)) {
            String type = NetworkUtil.getNetworkType();
            Log.d(Constants.TAG, mNetworkBroadcastReceiver + "网络状态变化了 type=" + type);
            if (mNetworkObserver != null) {
                mNetworkObserver.post(type);
            }
        }

    }


}
