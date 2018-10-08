package com.example.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.boundservice.BoundService.MyBinder;

public class MainActivity extends AppCompatActivity {

    private BoundService boundService;
    private boolean isServiceBound = false;

    private TextView timestampText;
    private Button printTimestampButton;
    private Button stopServiceButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("LOG_TAG", "in onCreate <- MainActivity");

        timestampText = findViewById(R.id.timestamp_text);
        printTimestampButton = findViewById(R.id.print_timestamp);
        stopServiceButon = findViewById(R.id.stop_service);

        printTimestampButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceBound) {
                    timestampText.setText(boundService.getTimestamp());
                }
            }
        });

        stopServiceButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceBound) {
                    unbindService(mServiceConnection);
                    isServiceBound = false;
                }

                Intent intent = new Intent(MainActivity.this, BoundService.class);
                stopService(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("LOG_TAG", "in onStart <- MainActivity");
        Intent intent = new Intent(this, BoundService.class);
        startService(intent);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("LOG_TAG", "in onStop <- MainActivity");
        if (isServiceBound) {
            unbindService(mServiceConnection);
            isServiceBound = false;
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBinder myBinder = (MyBinder) service;
            boundService = myBinder.getService();
            isServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBound = false;
        }
    };

}
