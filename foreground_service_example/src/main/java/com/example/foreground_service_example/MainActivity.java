package com.example.foreground_service_example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startForegroundButton = (Button) findViewById(R.id.button1);
        Button stopForegroundButton = (Button) findViewById(R.id.button2);
        Button stopServiceButton = (Button) findViewById(R.id.button3);

        startForegroundButton.setOnClickListener(this);
        stopForegroundButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent startForegroundIntent = new Intent(this, ForegroundService.class);
                startForegroundIntent.setAction(Constants.ACTION.START_FOREGROUND_ACTION);
                startService(startForegroundIntent);
                break;
            case R.id.button2:
                Intent stopForegroundIntent = new Intent(this, ForegroundService.class);
                stopForegroundIntent.setAction(Constants.ACTION.STOP_FOREGROUND_ACTION);
                startService(stopForegroundIntent);
                break;
            case R.id.button3:
                Intent stopIntent = new Intent(this, ForegroundService.class);
                stopIntent.setAction(Constants.ACTION.STOP_SERVICE_ACTION);
                startService(stopIntent);
                break;
            default:
                break;
        }
    }
}
