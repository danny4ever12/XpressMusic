package com.danny.musicplayer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


public class NotificationPanel {
   
	private Context parent;
	private NotificationManager nManager;
	private Notification.Builder nBuilder;
	private RemoteViews remoteView;

	public NotificationPanel(Context parent) {
	    // TODO Auto-generated constructor stub
	    this.parent = parent;
	    nBuilder = new Notification.Builder(parent)
	    .setContentTitle("Parking Meter")
	    .setSmallIcon(R.drawable.ic_launcher)
	    .setOngoing(true);

	    remoteView = new RemoteViews(parent.getPackageName(), R.layout.notificationview);

	    //set the button listeners
	    setListeners(remoteView);
	    nBuilder.setContent(remoteView);

	    nManager = (NotificationManager) parent.getSystemService(Context.NOTIFICATION_SERVICE);
	    nManager.notify(2, nBuilder.getNotification());
	}

	public void setListeners(RemoteViews view){
	    //listener 1
	    Intent volume = new Intent(parent,NotificationReturnSlot.class);
	    volume.putExtra("DO", "volume");
	    PendingIntent btn1 = PendingIntent.getActivity(parent, 0, volume, 0);
	    view.setOnClickPendingIntent(R.id.btn1, btn1);

	    //listener 2
	    Intent stop = new Intent(parent, NotificationReturnSlot.class);
	    stop.putExtra("DO", "stop");
	    PendingIntent btn2 = PendingIntent.getActivity(parent, 1, stop, 0);
	    view.setOnClickPendingIntent(R.id.btn2, btn2);
	}

	public void notificationCancel() {
	    nManager.cancel(2);
	}
	}    

