package com.jun.znews.ui.news.news;

import com.jun.znews.ThisApp;
import com.jun.znews.entity.NewsDetail;
import com.jun.znews.net.ApiConstants;
import com.jun.znews.net.NewsApiService;
import com.jun.znews.net.RetrofitConfig;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsModel implements INewsModel {

    Retrofit retrofit;

    @Override
    public Observable getData(String id,String action,int num) {

        Cache cache = new Cache(new File(ThisApp.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addInterceptor(RetrofitConfig.sQueryParameterInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.sIFengApi)
                .client(builder.build())
                .build();

        NewsApiService newsApiService = retrofit.create(NewsApiService.class);

        Observable<List<NewsDetail>> observable = newsApiService.getNewsDetail(id, action, num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return observable ;
    }


}
