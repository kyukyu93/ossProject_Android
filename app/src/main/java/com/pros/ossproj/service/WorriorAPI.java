package com.pros.ossproj.service;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WorriorAPI extends AsyncTask<String, Void, String> {
    String authKey = "7c13af54895a363820b454255aa7b9f7";
    private String str, receiveMsg;

    @Override
    protected String doInBackground(String... params) {
        URL url = null;

        Log.d("param1", "doInBackground: "+params[0].toString());
        Log.d("param2", "doInBackground: "+params[1].toString());
        Log.d("param3", "doInBackground: "+params[2].toString());
        Log.d("param3", "doInBackground: "+params[3].toString());
        try {
            url = new URL("http://3.37.76.18:13800/api/tracking"+"?time="+params[0].toString()+"&accuracy="+params[1].toString()+"&latitude="+params[2].toString()+"&longitude="+params[3].toString());
            // 서버 URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("WorriorAuthKey", authKey);
            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg); reader.close();
            } else {
                Log.i("결과", conn.getResponseCode() + "Error");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}