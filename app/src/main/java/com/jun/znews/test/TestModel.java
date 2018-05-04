package com.jun.znews.test;

import java.util.ArrayList;
import java.util.List;

public class TestModel implements IModel{

    private List<TestBean> listData;


    public TestModel() {
        this.listData = new ArrayList<>();
    }
    @Override
    public void getTestData(ICallBack<List<TestBean>> callBack) {
        //数据操作
        callBack.onFail("nonono");
        //数据操作
        callBack.onSuccess(listData);
    }

    @Override
    public List<TestBean> getAdapterData() {
        //数据操作
        return null;
    }
}
