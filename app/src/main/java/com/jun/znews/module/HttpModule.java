package com.jun.znews.module;


import com.jun.znews.ThisApp;
import com.jun.znews.net.ApiConstants;
import com.jun.znews.net.NewsApi;
import com.jun.znews.net.NewsApiService;
import com.jun.znews.net.RetrofitConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpModule {

    private static OkHttpClient.Builder provideOkHttpClient() {
        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(ThisApp.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        return new OkHttpClient().newBuilder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.sLoggingInterceptor)
                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addInterceptor(RetrofitConfig.sQueryParameterInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS);
    }


    public static NewsApi provideNewsApis() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient().build());

        return NewsApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(NewsApiService.class));
    }



}
