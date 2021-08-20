package com.pros.ossproj.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BroadCast extends BroadcastReceiver {
    private PatternDBHelper patternDBHelper;
    private WorriorGPS worriorGPS;
    static Boolean worriorReceive = false;

    WorriorPattern worriorPattern = new WorriorPattern();

    public void saveOnDataPatternDatabase(Context context, String strDo, String strLocation)
    {
        if (patternDBHelper == null) {
            patternDBHelper = new PatternDBHelper(context, "Pattern", null, 1);
        }
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = sdfNow.format(new Date(System.currentTimeMillis()));

        WorriorPattern worriorPattern = new WorriorPattern();
        worriorPattern.setAct_date(time);
        worriorPattern.setAct_do(strDo);
        worriorPattern.setAct_location(strLocation);

        patternDBHelper.addWorriorPatternData(worriorPattern);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String strLocation = "";

        String action = intent.getAction();
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.d("onReceive()", "부팅완료");
        }
        if (Intent.ACTION_SCREEN_ON == intent.getAction()) {
            Log.d("onReceive()", "스크린 ON");
            if (patternDBHelper == null) {
                patternDBHelper = new PatternDBHelper(context, "Pattern", null, 1);
            }

            worriorGPS = new WorriorGPS(context);
            // GPS 사용유무 가져오기
            if (worriorGPS.isGetLocation()) {

                double latitude = worriorGPS.getLatitude();
                double longitude = worriorGPS.getLongitude();
                //Toast.makeText(getApplicationContext(),"당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude,Toast.LENGTH_LONG).show();
                strLocation = String.format(latitude + "," + longitude);
            } else {
                // GPS 를 사용할수 없으므로
                strLocation = "0,0";
            }

            saveOnDataPatternDatabase(context, "ON", strLocation);
            patternDBHelper.readWorriorDatabasePattern();
        }
        if (Intent.ACTION_SCREEN_OFF == intent.getAction()) {
            Log.d("onReceive()", "스크린 OFF");
            if (patternDBHelper == null) {
                patternDBHelper = new PatternDBHelper(context, "Pattern", null, 1);
            }

            worriorGPS = new WorriorGPS(context);
            // GPS 사용유무 가져오기
            if (worriorGPS.isGetLocation()) {
                double latitude = worriorGPS.getLatitude();
                double longitude = worriorGPS.getLongitude();
                //Toast.makeText(getApplicationContext(),"당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude,Toast.LENGTH_LONG).show();
                strLocation = String.format(latitude + "," + longitude);
            } else {
                // GPS 를 사용할수 없으므로
                strLocation = "0,0";
            }
            saveOnDataPatternDatabase(context, "OFF", strLocation);
            patternDBHelper.readWorriorDatabasePattern();
        }
        /*
        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
            Log.d("onReceive()", "문자가 수신되었습니다");

        }
        */
    }
}
