package com.pros.ossproj.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class WorriorService extends Service {

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        System.out.println("onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println("서비스 등록");
    }
}