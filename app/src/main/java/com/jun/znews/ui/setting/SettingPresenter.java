package com.jun.znews.ui.setting;

import android.content.Context;

import com.jun.znews.ThisApp;
import com.jun.znews.ui.base.BasePresenter;
import com.jun.znews.utils.DataCleanManager;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SettingPresenter extends BasePresenter<ISettingContract.ISettingView, ISettingContract.ISettingModel> implements ISettingContract.ISettingPresenter {

    private Context context;

    public SettingPresenter(ISettingContract.ISettingView mView) {
        super(mView);
        this.context = ThisApp.getContext();
    }

    @Override
    protected ISettingContract.ISettingModel createModel() {
        return null;
    }

    @Override
    public void getCache() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String totalCacheSize = DataCleanManager.getTotalCacheSize(context);
                emitter.onNext(totalCacheSize);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}
                    @Override
                    public void onNext(String s) {
                        mView.setCache(s);
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.setCache(null);
                    }
                    @Override
                    public void onComplete() {}
                });
    }

    @Override
    public void clearCache() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                DataCleanManager.clearAllCache(context);
                String totalCacheSize = DataCleanManager.getTotalCacheSize(context);
                emitter.onNext(totalCacheSize);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}
                    @Override
                    public void onNext(String s) {
                        mView.setCache(s);
                        mView.setCacheClear();
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.setCache(null);
                    }
                    @Override
                    public void onComplete() {}
                });
    }


    @Override
    public void checkUpdate() {
        mView.setUpdate(false);
    }
}
