package com.jun.znews.ui.news.Presenter;

import com.jun.znews.bean.NewsOtherVideo;
import com.jun.znews.bean.NewsVideo;
import com.jun.znews.net.RxSchedulers;
import com.jun.znews.ui.base.BasePresenter;
import com.jun.znews.ui.news.Contract.IVideoContract;
import com.jun.znews.ui.news.model.VideoModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

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
        Observable<NewsVideo> observable = mModel.getData(guid);
        observable
                .compose(RxSchedulers.<NewsVideo>applySchedulers())
                .compose(mView.<NewsVideo>bindToLife())
                .subscribe(new Observer<NewsVideo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onNext(@NonNull NewsVideo videoBean) {
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
        Observable<NewsOtherVideo> observable = mModel.getOtherData(guid);
        observable
                .compose(RxSchedulers.<NewsOtherVideo>applySchedulers())
                .compose(mView.<NewsOtherVideo>bindToLife())
                .subscribe(new Observer<NewsOtherVideo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onNext(@NonNull NewsOtherVideo data) {
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
