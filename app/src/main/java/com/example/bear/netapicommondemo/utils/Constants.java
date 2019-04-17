package com.example.bear.netapicommondemo.utils;

import android.os.Environment;

import java.io.File;

public class Constants {
    public static final File OK_CACHE_DIR = new File(getCachePath());
    public static final long CACHE_SIZE = 1024*1024*300;
    private static String getCachePath() {
        String cachePath = "";
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            cachePath = Environment.getExternalStorageDirectory().getPath();
        } else {
            cachePath = Environment.getDataDirectory().getPath();
        }
        return cachePath;
    }
}
