package com.jun.znews.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jun.znews.R;
import com.trello.rxlifecycle2.components.support.RxFragment;


public abstract  class BaseFragment<T extends BasePresenter>  extends RxFragment  {

    protected T basePresenter;
    protected View mView ;
    protected Button loadFail ;
    protected TextView  loadingData ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter = initPresent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView = inflater.inflate(getLayout(),container,false) ;
            loadingData = mView.findViewById(R.id.img_loadingData) ;
            loadFail = mView.findViewById(R.id.img_loadFail) ;
        }
        loadingData.setVisibility(View.VISIBLE);
        loadFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingData.setVisibility(View.VISIBLE);
                loadFail.setVisibility(View.GONE);
                reload();
            }
        });
        init();
        prepare();
        return mView ;
    }


    protected void loadSucceed(){
        loadingData.setVisibility(View.GONE);
        loadFail.setVisibility(View.GONE);
    }
    protected void loadFail(){
        loadFail.setVisibility(View.VISIBLE);
        loadingData.setVisibility(View.GONE);
    }

    public abstract T initPresent();

    public abstract int getLayout();

    public abstract void init();

    public abstract void prepare();

    public abstract void reload();


}
