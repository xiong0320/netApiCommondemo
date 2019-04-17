package com.example.bear.netapicommondemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NetUtil {
    public static boolean isConnect(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnect = false;
        if (cm.getActiveNetworkInfo()!=null ) {
            return cm.getActiveNetworkInfo().isConnected();
        }
        return isConnect;
    }


    public static String parseUrl(String url, HashMap<String,Object> params) {
        String finalUrl = url;
        if (params==null || params.isEmpty()) {
            return finalUrl;
        }
        StringBuilder stringBuilder = new StringBuilder(url);
        if (url.endsWith("?")) {
            stringBuilder.append("?");
        }
        Iterator<HashMap.Entry<String,Object>> iterator = params.entrySet().iterator();
        HashMap.Entry<String,Object> item = null;
        boolean isNotFirst = false;
        while (iterator.hasNext()) {
            if (isNotFirst) {
                stringBuilder.append("&");
            }
            isNotFirst = true;
            item = iterator.next();
            stringBuilder.append(item.getKey()).append("=").append(item.getValue());
        }
        finalUrl = stringBuilder.toString();
        return finalUrl;
    }
}
