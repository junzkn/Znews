package com.jun.znews.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected T basepresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        basepresenter = initPresent();
        onPrepare();
    }


    abstract T initPresent();

    abstract int getLayout();

    abstract void initView();

    abstract void onPrepare();
}
