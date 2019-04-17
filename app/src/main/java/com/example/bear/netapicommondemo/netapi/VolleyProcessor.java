package com.example.bear.netapicommondemo.netapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bear.netapicommondemo.utils.NetUtil;

import java.util.HashMap;

public class VolleyProcessor implements IHttpProcessor {
    private static RequestQueue mQueue = null;

    public VolleyProcessor(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void post(String url, HashMap<String, Object> params, final ICallBack callBack) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, NetUtil.parseUrl(url,params), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        }, error -> callBack.onError(error.getMessage()));
        mQueue.add(stringRequest);
    }
}
