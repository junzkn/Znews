package com.jun.znews.ui.news.content;

import com.jun.znews.entity.NewsArticleBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

public interface IArticleReadView {

    void setData(NewsArticleBean newsArticleBean);

    LifecycleTransformer bindToLife();
}
