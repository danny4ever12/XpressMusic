package com.danny.musicplayer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NotificationReturnSlot extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    String action = (String) getIntent().getExtras().get("DO");
    if (action.equals("volume")) {
        Log.i("NotificationReturnSlot", "volume");
     
        //code for button 1
     
    } else if (action.equals("stopNotification")) {
    
    	//code for button2
        
    	Log.i("NotificationReturnSlot", "stopNotification");
     }
     finish();
    }
}