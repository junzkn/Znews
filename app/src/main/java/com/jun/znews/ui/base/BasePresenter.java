package com.jun.znews.ui.base;

import com.jun.znews.ui.news.IModel;
import com.jun.znews.ui.news.IView;

/**
 * desc:
 * author: Will .
 * date: 2017/9/2 .
 */

public class BasePresenter<T extends BaseFragment>  {
    public IView mView;
    public IModel mModel ;
}