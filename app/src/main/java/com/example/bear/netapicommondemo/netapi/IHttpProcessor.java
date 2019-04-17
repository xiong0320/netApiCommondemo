package com.example.bear.netapicommondemo.netapi;

import java.util.HashMap;

public interface IHttpProcessor {
    void post(String url, HashMap<String,Object> params,ICallBack callBack);
}
