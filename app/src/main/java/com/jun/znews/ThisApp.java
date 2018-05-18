package com.jun.znews;


import android.support.v7.app.AppCompatDelegate;

import com.jun.znews.common.SharedPreferencesConstance;
import com.jun.znews.utils.SharedPreferencesUtil;

import org.litepal.LitePalApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

public class ThisApp extends LitePalApplication {
    private static ThisApp instance = null ;

    @Override
    public void onCreate() {
        super.onCreate();
        BGASwipeBackHelper.init(this,null);
        setNightMode();

        ThisApp.instance = this ;
    }

    public static ThisApp getInstance (){
        return instance ;
    }



    private void setNightMode() {
        SharedPreferencesUtil sp = SharedPreferencesUtil.getInstance(this);
        AppCompatDelegate.setDefaultNightMode(sp.getBooleanValue(SharedPreferencesConstance.NIGHT_MODE)?
        AppCompatDelegate.MODE_NIGHT_YES :AppCompatDelegate.MODE_NIGHT_NO);
    }


}
