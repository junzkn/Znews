package com.jun.znews.ui.news.Presenter;

import com.jun.znews.bean.NewsArticleBean;
import com.jun.znews.net.RxSchedulers;
import com.jun.znews.ui.base.BasePresenter;
import com.jun.znews.ui.news.Contract.IArticleContract;
import com.jun.znews.ui.news.model.ArticleModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ArticlePresenter extends BasePresenter<IArticleContract.IArticleView,IArticleContract.IArticleModel> implements IArticleContract.IArticlePresenter {

    public ArticlePresenter(IArticleContract.IArticleView mView) {
        super(mView);
    }

    @Override
    protected IArticleContract.IArticleModel createModel() {
        return new ArticleModel();
    }

    @Override
    public void loadData( String aid) {
        Observable<NewsArticleBean> observable = mModel.getData(aid);
        observable
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
