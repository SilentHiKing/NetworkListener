package com.hiking.test;

import android.app.Application;

import com.hiking.networklistener.NetworkManager;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.getIns().init(this);
    }


}
