package com.jun.znews;

import android.app.Application;

public class ThisApp extends Application {
    private static ThisApp instance = null ;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this ;
    }

    public static ThisApp getInstance (){
        return instance ;
    }
}
