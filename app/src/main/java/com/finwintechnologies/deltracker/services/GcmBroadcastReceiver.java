package com.finwintechnologies.deltracker.services;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;


import androidx.legacy.content.WakefulBroadcastReceiver;

import com.finwintechnologies.deltracker.R;

import static androidx.legacy.content.WakefulBroadcastReceiver.startWakefulService;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // This is the Intent to deliver to our service.
        Intent service = new Intent(context, SimpleWakefulService.class);
        MediaPlayer mp = MediaPlayer.create(context, R.raw.notitone);
        mp.start();
        // Start the service, keeping the device awake while it is launching.
        Log.i("SimpleWakefulReceiver", "Starting service @ " + SystemClock.elapsedRealtime());
        startWakefulService(context, service);
    }
}
