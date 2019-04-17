package com.example.bear.netapicommondemo.netapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpHelper implements IHttpProcessor {
    private static HttpHelper instance;

    private HttpHelper() {
    }

    public static HttpHelper getInstance (){
        synchronized (HttpHelper.class) {
            if (instance == null) {
                instance = new HttpHelper();
            }
        }
        return instance;

    }
    private static IHttpProcessor mIHttpProcessor;
    public static void init(IHttpProcessor iHttpProcessor) {
        mIHttpProcessor= iHttpProcessor;
    }
    @Override
    public void post(String url, HashMap<String, Object> params, ICallBack callBack) {
        String finalUrl = appendParams(url,params);
        mIHttpProcessor.post(finalUrl,params,callBack);
    }

    private String appendParams(String url, HashMap<String,Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        StringBuffer urlBuilder = new StringBuffer(url);
        if (urlBuilder.indexOf("?")< 0) {
            urlBuilder.append("?");
        } else {
            if (!urlBuilder.toString().endsWith("?")) {
                urlBuilder.append("&");
            }
        }
        for (Map.Entry<String ,Object> entry:params.entrySet()) {
            urlBuilder.
                    append("&" +entry.getKey()).
                    append("=").
                    append(encode(entry.getValue().toString())
                    );
        }
        return urlBuilder.toString();
    }
    private static String encode(String s) {
        try {
            return URLEncoder.encode(s,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
