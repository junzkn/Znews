package com.jun.znews.ui.news.newsFragment;

import com.jun.znews.bean.NewsDetail;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

public interface INewsView {

    void setAdapterData(List<NewsDetail.ItemBean> newsDetails);

    void setMoreAdapterData(List<NewsDetail.ItemBean> newsDetails);

    void showNumber(int number);

    LifecycleTransformer bindToLife();



}
