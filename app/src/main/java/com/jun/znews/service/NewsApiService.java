package com.jun.znews.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {


    @GET("api_vampire_article_detail")
    Call<String> getNewsDetail(@Query("aid") String aid);
}
