package com.jun.znews.ui.news.main;

import com.jun.znews.bean.Channel;

import java.util.List;

public interface IMainNewsView {
    void setData(List<Channel> myChannels, List<Channel> otherChannels);
}
