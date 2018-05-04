package com.jun.znews.test;

import java.util.List;

public interface IContract
{
    interface IView
    {
        void showToast(String msg);

        void refreshAdapter();

        void onEmpty();
    }

    interface IPresenter
    {

        List<TestBean> getAdapterData();
    }
}
