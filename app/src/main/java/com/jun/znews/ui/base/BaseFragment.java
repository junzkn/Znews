package com.jun.znews.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jun.znews.ui.news.IPresenter;


public abstract  class BaseFragment<T extends IPresenter>  extends Fragment {
    protected T basepresenter;
    protected View mView ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basepresenter = initPresent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView = inflater.inflate(getLayout(),container,false) ;
        }
        initView();
        onPrepare();
        return mView ;
    }


    public abstract T initPresent();

    public abstract int getLayout();

    public abstract void initView();

    public abstract void onPrepare();
}
