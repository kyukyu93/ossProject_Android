package com.pros.ossproj.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorriorService extends Service
{
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000 * 60 * 30;
    private static final float LOCATION_DISTANCE = 100f;
    private WorriorDBHelper locationDBHelper;

    public void saveOnDataLocationDatabase(Context context, String strAccuracy, String strLatitude, String strLongitude)
    {
        if (locationDBHelper == null) {
            locationDBHelper = new WorriorDBHelper(context, "Location", null, 1);
        }
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = sdfNow.format(new Date(System.currentTimeMillis()));
        WorriorLocation lionaLocation = new WorriorLocation();
        lionaLocation.setTime(time);
        lionaLocation.setAccuracy(strAccuracy);
        lionaLocation.setLatitude(strLatitude);
        lionaLocation.setLongitude(strLongitude);
        locationDBHelper.addWorriorLocationData(lionaLocation);
    }


    private class LocationListener implements android.location.LocationListener
    {
        Location mLastLocation;

        public LocationListener(String provider)
        {
            Log.d("LocationListener", " LocationListener : " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location)
        {
            if (location.getAccuracy() <= 100)
            {
                Log.d("onLocationChanged < 100", " getLatitude : " + location.getLatitude());
                Log.d("onLocationChanged < 100", " getLongitude : " + location.getLongitude());
                Log.d("onLocationChanged < 100", " getAccuracy : " + location.getAccuracy());
                mLastLocation.set(location);
                saveOnDataLocationDatabase(getApplicationContext(), location.getAccuracy()+"",location.getLatitude()+"", location.getLongitude()+"");
            }
            else
            {
                Log.d("onLocationChanged > 100", " getLatitude : " + location.getLatitude());
                Log.d("onLocationChanged > 100", " getLongitude : " + location.getLongitude());
                Log.d("onLocationChanged > 100", " getAccuracy : " + location.getAccuracy());
            }
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Log.d("onProviderDisabled", "onProviderDisabled: "+ provider);
        }

        @Override
        public void onProviderEnabled(String provider)
        {
            Log.d("onProviderEnabled", "onProviderEnabled: "+ provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            Log.d("onStatusChanged", "onStatusChanged: "+ provider);
        }
    }

    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };
    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d("onStartCommand", "onStartCommand");

        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate()
    {
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.e("onCreate Exception1", "SecurityException");
        } catch (IllegalArgumentException ex) {
            Log.e("onCreate Exception1", "IllegalArgumentException");
        }
        try {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.e("onCreate Exception2", "SecurityException");
        } catch (IllegalArgumentException ex) {
            Log.e("onCreate Exception2", "IllegalArgumentException");
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                }
            }
        }
    }

    private void initializeLocationManager() {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        //Log.d("onCreate()","브로드캐스트리시버");
    }
}