package com.jun.znews.ui.news.Contract;

import com.jun.znews.bean.NewsArticleBean;
import com.jun.znews.bean.NewsOtherVideoBean;
import com.jun.znews.bean.NewsVideoBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

import io.reactivex.Observable;

public interface IVideoContract {


    interface IVideoModel {
        Observable getData(String guid) ;
        Observable getOtherData(String guid) ;
    }

    interface IVideoPresenter {
        //加载数据
        void loadData(String guid);
        void loadOtherData(String guid);
    }

    interface IVideoView {
        void setData(NewsVideoBean.VideoBean newsVideoBean);
        void setOtherData(List<NewsOtherVideoBean.GuidRelativeVideoInfoBean> data) ;
        LifecycleTransformer bindToLife();
    }
}
