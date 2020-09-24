package com.dbit.tryfirebase2;

import android.app.NotificationManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService  extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("MyFirebaseMsgService", "Nilesh");
        Log.d("MyFirebaseMsgService", "From: " + remoteMessage.getFrom());

       if(remoteMessage.getNotification() !=null){
            String title = remoteMessage.getNotification().getTitle();
            String msg = remoteMessage.getNotification().getBody();

            NotificationHelper.displayNotification(getApplicationContext(),title,msg);
        }
       else
        {
            Log.d("MyFirebaseMsgService", "From: " + remoteMessage.getFrom());
            // Check if remoteMessage.getNotification() == null ??
            Log.d("MyFirebaseMsgService", "Notification Message Body: " + remoteMessage.getNotification().getBody());
            Toast.makeText(getApplicationContext(),"notification is null",Toast.LENGTH_LONG);
        }
    }
}
