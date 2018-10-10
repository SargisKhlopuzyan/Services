package com.example.foreground_service_example;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class ForegroundService extends Service {

    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case of bound services.
        Log.e("LOG_TAG", "In onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("LOG_TAG", "*** In onStartCommand ***");

        if (intent  == null || intent.getAction() == null) {
            return START_STICKY;
        }

        if (intent.getAction().equals(Constants.ACTION.START_FOREGROUND_ACTION)) {
            Log.e("LOG_TAG", "Received Start Foreground Intent ");

            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

            Intent previousIntent = new Intent(this, ForegroundService.class);
            previousIntent.setAction(Constants.ACTION.PREV_ACTION);
            PendingIntent pendingPreviousIntent = PendingIntent.getService(this, 0, previousIntent, 0);

            Intent playIntent = new Intent(this, ForegroundService.class);
            playIntent.setAction(Constants.ACTION.PLAY_ACTION);
            PendingIntent pendingPlayIntent = PendingIntent.getService(this, 0, playIntent, 0);

            Intent nextIntent = new Intent(this, ForegroundService.class);
            nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
            PendingIntent pendingNextIntent = PendingIntent.getService(this, 0, nextIntent, 0);

//            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.a_kh);
            Notification notification = new NotificationCompat.Builder(this, Constants.MEDIA_PLAYER.CHANNEL_ID)
                    .setContentTitle("Truiton Music Player")
                    .setTicker("Truiton Music Player")
                    .setContentText("My Music")
                    .setSmallIcon(R.drawable.a_kh)
                    .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(icon).bigLargeIcon(null))
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .addAction(android.R.drawable.ic_media_previous, "Previous", pendingPreviousIntent)
                    .addAction(android.R.drawable.ic_media_play, "Play", pendingPlayIntent)
                    .addAction(android.R.drawable.ic_media_next, "Next", pendingNextIntent).build();

            startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, notification);

        } else if (intent.getAction().equals(Constants.ACTION.STOP_FOREGROUND_ACTION)) {
            Log.e("LOG_TAG","Received Stop Foreground Intent");
            stopForeground(true);
//            stopSelf();
        } else if (intent.getAction().equals(Constants.ACTION.STOP_SERVICE_ACTION)) {
            Log.e("LOG_TAG","Received Stop Service Intent");
            stopForeground(true);
            stopSelf();
        }

        else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
            Log.e("LOG_TAG","Clicked Previous");
        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
            Log.e("LOG_TAG","Clicked Play");
        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
            Log.e("LOG_TAG","Clicked Next");
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("LOG_TAG","In onDestroy");
    }
}
