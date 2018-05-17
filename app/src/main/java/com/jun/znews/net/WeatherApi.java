package com.jun.znews.net;

import com.jun.znews.bean.Weather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

   @GET("s6/weather/now")
   Observable<Weather> getWeather(@Query("location") String location, @Query("key")String key);

}
