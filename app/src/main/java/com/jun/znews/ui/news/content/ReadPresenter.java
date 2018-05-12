package com.jun.znews.ui.news.content;

import com.jun.znews.bean.NewsArticleBean;
import com.jun.znews.net.RxSchedulers;
import com.jun.znews.ui.base.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ReadPresenter extends BasePresenter implements IReadPresenter {

    public IReadView mView;
    public IReadModel mModel ;


    public ReadPresenter(IReadView articleReadView) {
        mView = articleReadView;
        mModel = new ReadModel();
    }




    @Override
    public void loadData( String aid) {
        Observable<NewsArticleBean> adapterData = mModel.getData(aid);
        adapterData
                .compose(RxSchedulers.<NewsArticleBean>applySchedulers())
                .compose(mView.<NewsArticleBean>bindToLife())
                .subscribe(new Observer<NewsArticleBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull NewsArticleBean articleBean) {
                        mView.setData(articleBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.setData(null);
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}
