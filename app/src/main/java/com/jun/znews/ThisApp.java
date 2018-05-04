package com.jun.znews;


import org.litepal.LitePalApplication;

public class ThisApp extends LitePalApplication {
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
