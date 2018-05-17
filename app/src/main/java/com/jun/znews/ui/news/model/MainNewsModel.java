package com.jun.znews.ui.news.model;

import com.jun.znews.bean.Channel;
import com.jun.znews.db.ChannelDao;
import com.jun.znews.ui.news.Contract.IMainNewsContarct;

import java.util.List;

public class MainNewsModel implements IMainNewsContarct.IMainNewsModel {
    @Override
    public List<Channel> getData() {
         return ChannelDao.getChannels();
    }
}
