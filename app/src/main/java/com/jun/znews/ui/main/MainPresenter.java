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

import com.jun.znews.bean.WeatherBean;
import com.jun.znews.net.ApiConstants;
import com.jun.znews.net.WeatherApiService;
import com.jun.znews.ui.base.BasePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.LOCATION_SERVICE;
import static com.jun.znews.net.ApiConstants.Weather_Key;

public class MainPresenter extends BasePresenter<IMainContract.IMainView, IMainContract.IMainModel> implements IMainContract.IMainPresenter {

    private LocationManager lm;
    private Context context;
    Retrofit retrofit;


    public MainPresenter(IMainContract.IMainView mView, Context context) {
        super(mView);
        this.context = context;
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
        lm.requestLocationUpdates(bestProvider, 0, 0, myLocationListener);

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
        retrofit.create(WeatherApiService.class)
                .getWeather(s, Weather_Key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(WeatherBean weatherBean) {
                        mView.setWeather(weatherBean.getHeWeather6().get(0));
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
