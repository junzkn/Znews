package com.jun.znews;


import org.litepal.LitePalApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

public class ThisApp extends LitePalApplication {
    private static ThisApp instance = null ;

    @Override
    public void onCreate() {
        super.onCreate();
        BGASwipeBackManager.getInstance().init(this) ;

        instance = this ;
    }

    public static ThisApp getInstance (){
        return instance ;
    }
}
