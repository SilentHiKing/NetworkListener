package com.hiking.networklistener;

import android.app.Application;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.getIns().init(this);
    }


}
