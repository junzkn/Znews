package com.jun.znews.net;

import com.jun.znews.bean.WeatherBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApiService {

   @GET("s6/weather/now")
   Observable<WeatherBean> getWeather(@Query("location") String location,@Query("key")String key);

}
