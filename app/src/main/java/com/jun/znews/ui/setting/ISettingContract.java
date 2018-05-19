package com.jun.znews.ui.setting;

public interface ISettingContract {
     interface ISettingView{
         void setCache(String s) ;
         void setCacheClear() ;
         void setUpdate(Boolean b) ;
     }
     interface ISettingPresenter{
         void getCache() ;
         void clearCache() ;
         void checkUpdate() ;
     }
     interface ISettingModel{}

}
