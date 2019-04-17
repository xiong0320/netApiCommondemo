package com.example.bear.netapicommondemo.repositry;


import com.example.bear.netapicommondemo.utils.AppExecutors;

public class WebInfoRepositry {

    private void fun(){
        AppExecutors appExecutors = new AppExecutors();
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
