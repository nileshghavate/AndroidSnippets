package com.dbit.trybroad;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Contacts;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

public class MyReceiver extends BroadcastReceiver {

    String context = "This is the notification example. Also the notification has a big message to display";
    int NOTIFICATION_ID = 7757;
    String CHANNEL_ID;


    NotificationManager notificationManager;
    PendingIntent resultPendingIntent, settingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {

        showNormalTextViewNotification(context);
        Log.i("hisham_debug", "Sucessfully Changed Time");
    }

    private void setNotificationChannel() {
        CHANNEL_ID = "my_channel_01";
        CharSequence name = "my_channel";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            notificationManager.createNotificationChannel(mChannel);
        }
    }


    public void showNormalTextViewNotification(Context context) {

        // Step 1: Set Notification Channel

        setNotificationChannel();

        // Step 2 : Set the intent and Action buttons
        // setActions();

        // Step 3: Build the content of Notification and pass to notification manager


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        // builder.setSmallIcon(R.drawable.ic_help);
        builder.setContentTitle("title");
        builder.setContentText(this.context);
        builder.setTicker("Hi this is ticker");
        builder.setAutoCancel(true);
        builder.addAction(R.drawable.ic_launcher_foreground, "Setting", settingIntent);

//        builder.setContentIntent(resultPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());


    }

}
