package com.example.bear.netapicommondemo.netapi;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallback<Result> implements ICallBack{
    @Override
    public void onSuccess(String result) {
        //将网络返回的jsonString装换成JSON对象
        Gson gson = new Gson();
        Class<?> clz = analysisClassInfo(this);
        try {
            if (String.class.getName().equals(clz.getName())) {
                onSuccess((Result)result);
                return;
            }
            Result result1 = (Result)gson.fromJson(result,clz);
            onSuccess(result1);
        } catch (Exception e) {
            e.printStackTrace();
            onError(e.getMessage());
        }

    }

    private Class<?> analysisClassInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[]  actualTypeArguments = ((ParameterizedType)genType).getActualTypeArguments();
        return (Class<?>) actualTypeArguments[0];
    }
    public abstract void onSuccess(Result result);


    @Override
    public void onError(String error) {

    }
}
