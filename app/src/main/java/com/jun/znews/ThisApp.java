package com.jun.znews;


import android.support.v7.app.AppCompatDelegate;

import org.litepal.LitePalApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

public class ThisApp extends LitePalApplication {
    private static ThisApp instance = null ;

    @Override
    public void onCreate() {
        super.onCreate();
        BGASwipeBackManager.getInstance().init(this) ;
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES );

        instance = this ;
    }

    public static ThisApp getInstance (){
        return instance ;
    }



    private void setNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES );
    }


}
