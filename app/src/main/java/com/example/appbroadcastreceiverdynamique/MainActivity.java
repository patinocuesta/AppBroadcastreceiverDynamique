package com.example.appbroadcastreceiverdynamique;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Create MyReceiver
    MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        // Initialize myReceiver in your onResume function
        // passing it a context and overriding the callback function
         myReceiver = new MyReceiver(this) {
            @Override
            public void airplaneModeChanged(boolean enabled) {
                if (enabled){
                    Log.d("AirplaneModeReceiver","Airplane mode changed to: enable ");
                    Toast.makeText(getBaseContext(),"Mode avion activé",Toast.LENGTH_LONG).show();
                }
                else {
                    Log.d("AirplaneModeReceiver","Airplane mode changed to: disable ");
                    Toast.makeText(getBaseContext(),"Mode avion desactivé",Toast.LENGTH_LONG).show();
                }
            }
        };
        myReceiver.register();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        // Unregister myReceiver
        if (myReceiver != null)
            myReceiver.unregister();
    }
}
