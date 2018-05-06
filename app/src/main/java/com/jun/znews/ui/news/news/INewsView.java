package com.jun.znews.ui.news.news;

import com.jun.znews.entity.NewsDetail;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

public interface INewsView {

    void setAdapterData(List<NewsDetail.ItemBean> newsDetails);

    void setMoreAdapterData(List<NewsDetail.ItemBean> newsDetails);

    void showNumber(int number);

    LifecycleTransformer bindToLife();



}
