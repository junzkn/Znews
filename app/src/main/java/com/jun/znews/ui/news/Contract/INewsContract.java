package com.jun.znews.ui.news.Contract;

import com.jun.znews.bean.NewsDetail;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

import io.reactivex.Observable;


public interface INewsContract {
    interface INewsModel {
        Observable getData(String id, String action, int num) ;
    }

    interface INewsPresenter {

        //加载数据
        void loadData(String id, String action, int num);

        //上拉加载数据
        void loadUpData(String id, String action, int num);


    }

    interface INewsView {

        void setAdapterData(List<NewsDetail.ItemBean> newsDetails);

        void setMoreAdapterData(List<NewsDetail.ItemBean> newsDetails);

        void showNumber(int number);

        LifecycleTransformer bindToLife();

    }
}
