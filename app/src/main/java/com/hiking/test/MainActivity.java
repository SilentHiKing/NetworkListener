package com.hiking.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hiking.networklistener.NetworkManager;
import com.hiking.networklistener.annotation.NetType;
import com.hiking.networklistener.annotation.Network;
import com.hiking.networklistener.bean.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkManager.getIns().registerObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getIns().unregisterObserver(this);
    }

    @Network
    private void aaa(String neyType) {
        Log.d(Constants.TAG, "neyType =" + neyType);
        switch (neyType) {
            case NetType.AUTO:
                Log.i(Constants.TAG, "AUTO");
                break;
            case NetType.CMNET:
                Log.i(Constants.TAG, "CMNET");
                break;
            case NetType.CMWAP:
                Log.i(Constants.TAG, "CMWAP");
                break;
            case NetType.WIFI:
                Log.i(Constants.TAG, "WIFI");
                break;
            case NetType.NONE:
                Log.i(Constants.TAG, "NONE");
                break;
            case NetType.MOBILE:
                Log.i(Constants.TAG, "MOBILE");
                break;
        }
    }
}
