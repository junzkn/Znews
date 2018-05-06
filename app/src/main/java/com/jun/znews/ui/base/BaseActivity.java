package com.jun.znews.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.trello.rxlifecycle2.components.RxActivity;


public abstract class BaseActivity<T extends BasePresenter> extends RxActivity {

    public T basePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        init();
        basePresenter = initPresent();
        prepare();
    }



    public abstract T initPresent();

    public abstract int getLayout();

    public abstract void init();

    public abstract void prepare();
}
