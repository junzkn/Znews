package com.jun.znews.ui.news;

import android.util.Log;

import com.jun.znews.ThisApp;
import com.jun.znews.entity.NewsDetail;
import com.jun.znews.net.BaseObserver;
import com.jun.znews.net.NewsApiService;
import com.jun.znews.net.RetrofitConfig;
import com.jun.znews.utils.NewsUtils;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsModel implements IModel {

    Retrofit retrofit;
    int page = 0;

    @Override
    public Observable getAdapterData() {

        Cache cache = new Cache(new File(ThisApp.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.sLoggingInterceptor)
                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addInterceptor(RetrofitConfig.sQueryParameterInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://api.iclient.ifeng.com/")
                .client(builder.build())
                .build();
        NewsApiService newsApiService = retrofit.create(NewsApiService.class);

        return newsApiService.getNewsDetail("SYLB10", "default",page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;
    }

    @Override
    public void getMoreAdapterDAta() {

    }







}
