package com.example.bear.netapicommondemo.netapi;

import android.content.Context;

import com.example.bear.netapicommondemo.utils.Constants;
import com.example.bear.netapicommondemo.utils.NetUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpProcessor implements IHttpProcessor {
    private OkHttpClient mOkHttpClient;
    private static Context mContext;
    @Override
    public void post(String url, HashMap<String, Object> params, final ICallBack callBack) {
        Request request = new Request.Builder().url(NetUtil.parseUrl(url,params)).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body().string());
                }
            }
        });
    }

    public OkHttpProcessor(Context context) {
        mContext = context;
        mOkHttpClient = getDefaultOkHttpClient();
    }

    public OkHttpClient getDefaultOkHttpClient() {
        OkHttpClient okHttpClient = null;
        okHttpClient =new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .cache(new Cache(Constants.OK_CACHE_DIR,Constants.CACHE_SIZE))
                .addInterceptor(cacheInterceptor)
                .build();
        return okHttpClient;
    }
    private static Interceptor cacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            /*无网的时候强制使用缓存*/
            if (!NetUtil.isConnect(mContext)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);

            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            if (!NetUtil.isConnect(mContext)) {
                String cacheControl = request.cacheControl().toString();
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 ; // tolerate 4-weeks stale
                return response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
}
