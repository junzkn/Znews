package com.jun.znews.test;

import java.util.List;

public class TestPresenter extends BasePresenter<TestActivity> implements IContract.IPresenter {
    private IContract.IView mView;
    private IModel mModel;

    public TestPresenter(IContract.IView iView) {
        this.mView = iView;
        this.mModel = new TestModel();
    }


    @Override
    void initData() {
        mModel.getTestData(new ICallBack<List<TestBean>>() {
            @Override
            public void onSuccess(List<TestBean> testBeans) {
                mModel.getAdapterData().addAll(testBeans);
                mView.refreshAdapter();
            }

            @Override
            public void onFail(String code) {
                mView.showToast(code);
                mView.onEmpty();
            }
        });
    }

    @Override
    public List<TestBean> getAdapterData() {
        return mModel.getAdapterData();
    }


}
