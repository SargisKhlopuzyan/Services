package com.example.service_and_broadcast_receiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

public class BroadcastService extends Service {

    private ArrayList<String> arrayList;

    public BroadcastService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Wont be called as service is not bound
        Log.e("LOG_TAG", "In onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("LOG_TAG", "In onCreate");

        arrayList = new ArrayList<String>();
        arrayList.add("Object 1");
        arrayList.add("Object 2");
        arrayList.add("Object 3");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.e("LOG_TAG", "In onStartCommand");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(MainActivity.BROADCAST_STRING_ACTION);
                broadcastIntent.putExtra(MainActivity.EXTRA_DATA, "Broadcast Data");
                sendBroadcast(broadcastIntent);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                broadcastIntent.setAction(MainActivity.BROADCAST_INTEGER_ACTION);
                broadcastIntent.putExtra(MainActivity.EXTRA_DATA, 10);
                sendBroadcast(broadcastIntent);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                broadcastIntent.setAction(MainActivity.BROADCAST_ARRAY_ACTION);
                broadcastIntent.putExtra(MainActivity.EXTRA_DATA, arrayList);
                sendBroadcast(broadcastIntent);
            }
        }).start();

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.e("LOG_TAG", "In onTaskRemoved");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("LOG_TAG", "In onDestroy");
    }
}
