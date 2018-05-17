package com.jun.znews.net;

import com.jun.znews.bean.NewsArticleBean;
import com.jun.znews.bean.NewsDetail;
import com.jun.znews.bean.NewsOtherVideoBean;
import com.jun.znews.bean.NewsVideoBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface NewsApiService {

    @GET("ClientNews")
    Observable<List<NewsDetail>> getNewsDetail(@Query("id") String id,
                                               @Query("action") String action,
                                               @Query("pullNum") int pullNum
    );

    @GET("api_vampire_article_detail")
    Observable<NewsArticleBean> getNewsArticleWithSub(@Query("aid") String aid);

    @GET
    Observable<NewsArticleBean> getNewsArticleWithCmpp(@Url String url,
                                                       @Query("aid") String aid);

    @GET("api_phoenixtv_details")
    Observable<NewsVideoBean> getNewsVideo(@Query("guid") String guid);

    @GET("getGuidRelativeVideoList")
    Observable<NewsOtherVideoBean> getOtherVideo(@Query("guid") String guid);

}
