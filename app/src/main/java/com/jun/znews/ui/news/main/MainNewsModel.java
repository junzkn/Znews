package com.jun.znews.ui.news.main;

import com.jun.znews.bean.Channel;
import com.jun.znews.db.ChannelDao;

import java.util.List;

public class MainNewsModel implements IMainNewsModel {
    @Override
    public List<Channel> getData() {
         return ChannelDao.getChannels();
    }
}
