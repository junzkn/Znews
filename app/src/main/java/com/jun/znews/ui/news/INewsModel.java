package com.jun.znews.ui.news;



import io.reactivex.Observable;

public interface INewsModel {
    Observable getData(String id,String action,int num) ;
}
