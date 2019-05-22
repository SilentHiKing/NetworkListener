package com.hiking.networklistener.annotation;

import android.support.annotation.StringDef;

@StringDef({NetType.AUTO, NetType.WIFI, NetType.CMNET, NetType.CMWAP, NetType.NONE, NetType.MOBILE})
public @interface NetType {
    //有网络，包括Wifi/gprs
    String AUTO = "AUTO";
    //wifi
    String WIFI = "WIFI";
    //PC/笔记本/PDA
    String CMNET = "CMNET";
    //手机端
    String CMWAP = "CMWAP";
    //没有网络
    String NONE = "NONE";


    //移动端
    String MOBILE = "MOBILE";
}
