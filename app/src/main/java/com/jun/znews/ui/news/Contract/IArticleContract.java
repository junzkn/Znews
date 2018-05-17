package com.jun.znews.ui.news.Contract;

import com.jun.znews.bean.NewsArticleBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observable;


public interface IArticleContract {


    interface IArticleModel {
        Observable getData(String aid) ;
    }

    interface IArticlePresenter {
        //加载数据
        void loadData(String aid);
    }

    interface IArticleView {
        void setData(NewsArticleBean newsArticleBean);
        LifecycleTransformer bindToLife();
    }
}
