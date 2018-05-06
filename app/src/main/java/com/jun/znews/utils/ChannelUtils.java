package com.jun.znews.utils;

import android.content.Context;

import com.jun.znews.R;
import com.jun.znews.entity.ChannelBean;

import java.util.ArrayList;
import java.util.List;

public class ChannelUtils {

//    public static List<ChannelBean> getChannel{
//
//    }
//    public static List<String> getChannelName{
//
//    }
//    public static List<String> getChannelID{
//
//    }
    public static List<ChannelBean> getALLChannel(Context context){
        List<ChannelBean> list = new ArrayList();
        String[] names = getALLChannelName(context);
        String[] ids = getALLChannelID(context);
        for(int i=0 ; i<names.length ; i++){
            boolean channelType = names[i].equals("头条") ;
            list.add(new ChannelBean(channelType,names[i],ids[i]));
        }
        return list;
    }
    public static String[] getALLChannelName(Context context){
        return context.getResources().getStringArray(R.array.news_channel_name);
    }
    public static String[] getALLChannelID(Context context){
        return context.getResources().getStringArray(R.array.news_channel_id);
    }


}
