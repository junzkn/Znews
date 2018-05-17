package com.jun.znews.ui.news.Contract;

import com.jun.znews.bean.Channel;

import java.util.List;


public interface IMainNewsContarct {
    interface IMainNewsModel {

        List<Channel> getData() ;
    }

    interface IMainNewsPresenter {

        void getChannel() ;
    }

    interface IMainNewsView {
        void setData(List<Channel> myChannels, List<Channel> otherChannels);
    }
}
