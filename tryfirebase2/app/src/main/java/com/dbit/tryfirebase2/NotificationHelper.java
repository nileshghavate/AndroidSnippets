package com.dbit.tryfirebase2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper {

    private static final String CHANNEL_ID ="nilesh";
    private static final String CHANNEL_NAME ="nilesh";
    private static final String CHANNEL_DESC ="nilesh NOTIFICATION";



    public static void displayNotification(Context context,String title, String body)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.ic_notificaiton_bell);
        mBuilder.setContentText(body);
        mBuilder.setContentTitle(title);
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat nmc= NotificationManagerCompat.from(context);
        nmc.notify(1,mBuilder.build());
    }

}
