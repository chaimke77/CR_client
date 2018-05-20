package com.example.user.cr_client.backend;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.user.cr_client.controller.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private Timer timer = new Timer();
    static final int UPDATE_INTERVAL = 1000 * 5;
    public static final String PARAM_OUT_MSG = "OUT_MESSAGE";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Log.d("MyService", "Start Sending message...");
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(MainActivity.mBroadcastStringAction);
                broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
                String currentDateandTime = sdf.format(new Date());
                broadcastIntent.putExtra(PARAM_OUT_MSG, "Service Timer: " + currentDateandTime);
                sendBroadcast(broadcastIntent);
                Log.d("MyService", "End Sending message...");
            }
        }, 1, 1000*10);

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

}
