package com.jun.znews.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.jun.znews.ThisApp;
import com.jun.znews.bean.Weather;
import com.jun.znews.common.SharedPreferencesConstance;
import com.jun.znews.net.ApiConstants;
import com.jun.znews.net.RetrofitConfig;
import com.jun.znews.net.WeatherApi;
import com.jun.znews.ui.base.BasePresenter;
import com.jun.znews.utils.SharedPreferencesUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.Context.LOCATION_SERVICE;
import static com.jun.znews.net.ApiConstants.Weather_Key;

public class MainPresenter extends BasePresenter<IMainContract.IMainView, IMainContract.IMainModel> implements IMainContract.IMainPresenter {

    private LocationManager lm;
    private Context context;
    Retrofit retrofit;


    public MainPresenter(IMainContract.IMainView mView) {
        super(mView);
        this.context = ThisApp.getContext();
    }

    @Override
    protected IMainContract.IMainModel createModel() {
        return null;
    }

    @Override
    public void getWeather() {
        lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setCostAllowed(true);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String bestProvider = lm.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //TODO
        }

        myLocationListener myLocationListener = new myLocationListener();
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, myLocationListener);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.Weather)
                .build();

    }


    private class myLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            getWeatherFromNet(longitude + "," + latitude) ;
            lm.removeUpdates(this);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e("location", "c");

        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e("location", "e");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e("location", "d");

        }
    }

    private void getWeatherFromNet(String s) {
        retrofit.create(WeatherApi.class)
                .getWeather(s, Weather_Key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Weather weather) {
                        mView.setWeather(weather.getHeWeather6().get(0));
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.setWeather(null);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }







}
