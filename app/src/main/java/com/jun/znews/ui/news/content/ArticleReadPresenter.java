package com.jun.znews.ui.news.content;

import com.jun.znews.entity.NewsArticleBean;
import com.jun.znews.net.RxSchedulers;
import com.jun.znews.ui.base.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ArticleReadPresenter extends BasePresenter implements IArticleReadPresenter {

    public IArticleReadView mView;
    public IArticleReadModel mModel ;


    public ArticleReadPresenter(IArticleReadView articleReadView) {
        mView = articleReadView;
        mModel = new ArticleReadModel();
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
                        //TODO
                        //mView.showFaild();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}
