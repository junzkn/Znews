package com.jun.znews.test;

import java.util.List;

public interface IModel {
    /**
     * 获取模拟数据
     */
    void getTestData(ICallBack<List<TestBean>> callBack);
    List<TestBean> getAdapterData();


}
