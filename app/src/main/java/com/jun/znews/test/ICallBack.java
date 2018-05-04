package com.jun.znews.test;

public interface ICallBack <T>{
    void onSuccess(T t);

    void onFail(String code);
}
