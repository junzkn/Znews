package com.jun.znews.bean;

public class LeftItemMenu {
    private int icon ;
    private String title ;

    public LeftItemMenu(int icon , String title){
        this.icon = icon ;
        this.title = title ;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
