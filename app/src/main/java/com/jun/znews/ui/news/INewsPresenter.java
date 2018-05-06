package com.jun.znews.ui.news;




public interface INewsPresenter {


    //加载数据
    void loadData (String id,String action,int num);

    //上拉加载数据
    void loadUpData(String id,String action,int num);



}
