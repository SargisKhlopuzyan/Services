package com.example.service_and_broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String BROADCAST_STRING_ACTION = "om.example.service_and_broadcast_receiver.string";
    public static final String BROADCAST_INTEGER_ACTION = "om.example.service_and_broadcast_receiver.integer";
    public static final String BROADCAST_ARRAY_ACTION = "om.example.service_and_broadcast_receiver.arraylist";
    public static final String EXTRA_DATA = "Data";

    private TextView textView;

    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview1);

        intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_STRING_ACTION);
        intentFilter.addAction(BROADCAST_INTEGER_ACTION);
        intentFilter.addAction(BROADCAST_ARRAY_ACTION);

        Intent serviceIntent = new Intent(this, BroadcastService.class);
        startService(serviceIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(broadcastReceiver);
        super.onPause();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            textView.setText(textView.getText() + "Broadcast From Service: \n");
            if (intent.getAction().equals(BROADCAST_STRING_ACTION)) {
                textView.setText(textView.getText().toString() + intent.getStringExtra(EXTRA_DATA) + "\n\n");
            } else if (intent.getAction().equals(BROADCAST_INTEGER_ACTION)) {
                textView.setText(textView.getText().toString() + intent.getIntExtra(EXTRA_DATA, 0) + "\n\n");
            } else if (intent.getAction().equals(BROADCAST_ARRAY_ACTION)) {
                textView.setText(textView.getText().toString() + intent.getStringArrayListExtra(EXTRA_DATA) + "\n\n");
                Log.e("LOG_TAG", "*** In onReceive ***");
                Intent stopIntent = new Intent(MainActivity.this, BroadcastService.class);
                stopService(stopIntent);
            }
        }
    };
}
