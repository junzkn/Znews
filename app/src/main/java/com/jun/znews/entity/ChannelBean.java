package com.jun.znews.entity;

public class ChannelBean {
    private String channelName ;
    private String channelID ;
    private boolean channelType ;

    public ChannelBean(boolean channelType,String channelName ,String channelID ){
        this.channelID = channelID ;
        this.channelName = channelName ;
        this.channelType = channelType ;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public boolean isChannelType() {
        return channelType;
    }

    public void setChannelType(boolean channelType) {
        this.channelType = channelType;
    }
}
