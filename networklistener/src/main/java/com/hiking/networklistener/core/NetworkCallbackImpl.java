package com.hiking.networklistener.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.hiking.networklistener.annotation.NetType;
import com.hiking.networklistener.bean.Constants;

/**
 * 回调监听网络变化
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {

    private static NetworkObserver mNetworkObserver;
    private static NetworkCallbackImpl mNetworkCallbackImpl;


    public static void register(Context c, NetworkObserver observer) {
        mNetworkObserver = observer;
        if (mNetworkCallbackImpl == null) {
            mNetworkCallbackImpl = new NetworkCallbackImpl();
        }
        NetworkRequest request = new NetworkRequest.Builder().build();
        ConnectivityManager cmgr = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cmgr != null) {
            cmgr.registerNetworkCallback(request, mNetworkCallbackImpl);
        }
    }

    public static void unregister(Context c) {
        ConnectivityManager cmgr = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cmgr != null && mNetworkCallbackImpl != null) {
            cmgr.unregisterNetworkCallback(mNetworkCallbackImpl);
        }
        mNetworkObserver = null;
        mNetworkCallbackImpl = null;
    }

    @Override
    public void onAvailable(Network network) {
        super.onAvailable(network);
        Log.e(Constants.TAG, "网络连接了");
    }

    @Override
    public void onLost(Network network) {
        super.onLost(network);
        Log.e(Constants.TAG, "网络断开了");
        if (mNetworkObserver != null) {
            mNetworkObserver.post(NetType.NONE);
        }
    }

    @Override
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.e(Constants.TAG, "wifi网络已连接");
                if (mNetworkObserver != null) {
                    mNetworkObserver.post(NetType.WIFI);
                }
            } else {
                if (mNetworkObserver != null) {
                    mNetworkObserver.post(NetType.MOBILE);
                }
                Log.e(Constants.TAG, "移动网络已连接");
            }
        }
    }
}