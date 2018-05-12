package com.jun.znews.ui.news.content;

import com.jun.znews.bean.NewsArticleBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

public interface IReadView {

    void setData(NewsArticleBean newsArticleBean);

    LifecycleTransformer bindToLife();
}
