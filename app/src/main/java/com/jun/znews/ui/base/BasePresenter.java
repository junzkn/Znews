package com.jun.znews.ui.base;



public abstract class BasePresenter<T,T2>  {
    public T mView ;
    public T2 mModel ;

    public BasePresenter(T mView){
        this.mView = mView ;
        mModel = createModel() ;
    }

    protected abstract T2 createModel();

}