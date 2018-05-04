package com.jun.znews.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    public T basepresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        setContentView(getLayout());
        initView();
        basepresenter = initPresent();
        onPrepare();
    }



    public abstract T initPresent();

    public abstract int getLayout();

    public abstract void initView();

    public abstract void onPrepare();
}
