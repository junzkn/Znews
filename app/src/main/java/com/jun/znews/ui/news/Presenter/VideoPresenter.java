package com.jun.znews.ui.news.Presenter;

import android.util.Log;

import com.jun.znews.bean.NewsArticleBean;
import com.jun.znews.bean.NewsDetail;
import com.jun.znews.bean.NewsOtherVideoBean;
import com.jun.znews.bean.NewsVideoBean;
import com.jun.znews.net.RxSchedulers;
import com.jun.znews.ui.base.BasePresenter;
import com.jun.znews.ui.news.Contract.IVideoContract;
import com.jun.znews.ui.news.model.VideoModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class VideoPresenter extends BasePresenter<IVideoContract.IVideoView,IVideoContract.IVideoModel> implements IVideoContract.IVideoPresenter {



    public VideoPresenter(IVideoContract.IVideoView mView) {
        super(mView);
    }
    @Override
    protected IVideoContract.IVideoModel createModel() {
        return new VideoModel();
    }

    @Override
    public void loadData(String guid) {
        Observable<NewsVideoBean> observable = mModel.getData(guid);
        observable
                .compose(RxSchedulers.<NewsVideoBean>applySchedulers())
                .compose(mView.<NewsVideoBean>bindToLife())
                .subscribe(new Observer<NewsVideoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onNext(@NonNull NewsVideoBean videoBean) {
                        mView.setData(videoBean.getSingleVideoInfo().get(0));
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

    @Override
    public void loadOtherData(String guid) {
        Observable<NewsOtherVideoBean> observable = mModel.getOtherData(guid);
        observable
                .compose(RxSchedulers.<NewsOtherVideoBean>applySchedulers())
                .compose(mView.<NewsOtherVideoBean>bindToLife())
                .subscribe(new Observer<NewsOtherVideoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onNext(@NonNull NewsOtherVideoBean data) {
                        mView.setOtherData(data.getGuidRelativeVideoInfo());
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.setOtherData(null);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }


}
