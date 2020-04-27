package com.example.appbroadcastreceiverdynamique;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;

public abstract  class MyReceiver extends BroadcastReceiver {
    private Context context;
    public MyReceiver(Context context){
        this.context =  context;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
    // Capturing  mode airplane state after mode change
        if(Settings.System.getInt(
                context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0
        ) == 0) {
            airplaneModeChanged(false);
        } else {
            airplaneModeChanged(true);
        }
    }
     //Used to register the airplane mode reciever.
    public void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        context.registerReceiver(this, intentFilter);
    }
    //Used to unregister the airplane mode reciever.
    public void unregister() {
        context.unregisterReceiver(this);
    }
     //Called when airplane mode is changed.
    public abstract void airplaneModeChanged(boolean enabled);
}
