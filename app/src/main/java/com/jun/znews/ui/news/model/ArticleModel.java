package com.jun.znews.ui.news.model;

import com.jun.znews.ThisApp;
import com.jun.znews.bean.NewsArticleBean;
import com.jun.znews.net.ApiConstants;
import com.jun.znews.net.NewsApiService;
import com.jun.znews.net.RetrofitConfig;
import com.jun.znews.ui.news.Contract.IArticleContract;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleModel implements IArticleContract.IArticleModel {


    @Override
    public Observable getData(String aid) {
        Cache cache = new Cache(new File(ThisApp.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addInterceptor(RetrofitConfig.sQueryParameterInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.sIFengApi)
                .client(builder.build())
                .build();

        NewsApiService newsApiService = retrofit.create(NewsApiService.class);

        Observable<NewsArticleBean> observable ;
        if (aid.startsWith("sub")){
            observable = newsApiService.getNewsArticleWithSub(aid);
        }else {
            observable = newsApiService.getNewsArticleWithCmpp(ApiConstants.sGetNewsArticleCmppApi + ApiConstants.sGetNewsArticleDocCmppApi,aid);
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return observable ;
    }
}
