package com.jun.znews.utils;

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

import static android.content.Context.LOCATION_SERVICE;

public class LocationUtil {
    public static String get(Context context){
        LocationManager lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setCostAllowed(true);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String bestProvider = lm.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //TODO
            Log.e("location","noPermission") ;

        }
        lm.requestLocationUpdates(bestProvider, 0, 0, new myLocationListener());
        return "" ;
    }

    private static class myLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            Log.e("location","d"+location.getLatitude()) ;

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e("location","c") ;

        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e("location","e") ;

        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e("location","d") ;

        }
    }
}
