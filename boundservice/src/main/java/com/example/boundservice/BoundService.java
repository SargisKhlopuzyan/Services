package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

public class BoundService extends Service {

    private Chronometer chronometer;

    private IBinder binder = new MyBinder();

    public BoundService() {}

    // always
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("LOG_TAG", "in onCreate");
        chronometer = new Chronometer(this);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("LOG_TAG", "in onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
    // or
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("LOG_TAG", "in onBind");
        return binder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e("LOG_TAG", "in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("LOG_TAG", "in onUnbind");
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("LOG_TAG", "in onDestroy");
        chronometer.stop();
    }

    public String getTimestamp() {
        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        int hours = (int) (elapsedMillis / 3600000);
        int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
        int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;
        int millis = (int) (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours + ":" + minutes + ":" + seconds + ":" + millis;
    }

    public class MyBinder extends Binder {
        public BoundService getService(){
            return BoundService.this;
        }
    }

}
