package com.example.bear.netapicommondemo;

import android.app.Application;

import com.example.bear.netapicommondemo.netapi.HttpHelper;
import com.example.bear.netapicommondemo.netapi.OkHttpProcessor;
import com.example.bear.netapicommondemo.netapi.VolleyProcessor;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        HttpHelper.init(new VolleyProcessor(this));
        HttpHelper.init(new OkHttpProcessor(this));
    }
}
