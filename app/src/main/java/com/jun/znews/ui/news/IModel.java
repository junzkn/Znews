package com.jun.znews.ui.news;

import com.jun.znews.entity.NewsDetail;

import java.util.List;

import io.reactivex.Observable;

public interface IModel {
    Observable getAdapterData() ;
    void getMoreAdapterDAta() ;
}
