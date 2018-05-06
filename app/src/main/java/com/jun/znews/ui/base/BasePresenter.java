package com.jun.znews.ui.base;

import com.jun.znews.ui.news.INewsModel;
import com.jun.znews.ui.news.INewsView;

/**
 * desc:
 * author: Will .
 * date: 2017/9/2 .
 */

public class BasePresenter<T extends BaseFragment>  {
    public INewsView mView;
    public INewsModel mModel ;
}