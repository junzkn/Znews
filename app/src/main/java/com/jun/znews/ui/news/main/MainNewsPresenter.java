package com.jun.znews.ui.news.main;

import com.jun.znews.R;
import com.jun.znews.ThisApp;
import com.jun.znews.bean.Channel;
import com.jun.znews.db.ChannelDao;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainNewsPresenter implements IMainNewsPresenter{
    private IMainNewsView mView;
    private IMainNewsModel mModel ;

    public MainNewsPresenter(IMainNewsView mainNewsView){
        mView = mainNewsView;
        mModel = new MainNewsModel();
    }




    @Override
    public void getChannel() {
        List<Channel> channelList;
        List<Channel> myChannels = new ArrayList<>();
        List<Channel> otherChannels = new ArrayList<>();
        channelList = mModel.getData();
        if (channelList.size() < 1) {
            List<String> channelName = Arrays.asList(ThisApp.getContext().getResources()
                    .getStringArray(R.array.news_channel_name));
            List<String> channelId = Arrays.asList(ThisApp.getContext().getResources()
                    .getStringArray(R.array.news_channel_id));
            List<Channel> channels = new ArrayList<>();

            for (int i = 0; i < channelName.size(); i++) {
                Channel channel = new Channel();
                channel.setChannelId(channelId.get(i));
                channel.setChannelName(channelName.get(i));
                channel.setChannelType(i < 1 ? 1 : 0);
                channel.setChannelSelect(i < channelId.size() - 7);
                if (i < channelId.size() - 7) {
                    myChannels.add(channel);
                } else {
                    otherChannels.add(channel);
                }
                channels.add(channel);
            }
            DataSupport.saveAllAsync(channels).listen(new SaveCallback() {
                @Override
                public void onFinish(boolean success) {
                }
            });
            channelList = new ArrayList<>();
            channelList.addAll(channels);
        }else {
            channelList = ChannelDao.getChannels();
            Iterator<Channel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                Channel channel = iterator.next();
                if (!channel.isChannelSelect()) {
                    otherChannels.add(channel);
                    iterator.remove();
                }
            }
            myChannels.addAll(channelList);
        }
        mView.setData(myChannels, otherChannels);

    }
}
