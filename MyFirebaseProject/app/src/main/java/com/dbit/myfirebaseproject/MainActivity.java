package com.dbit.myfirebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;




import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fcmToken = FirebaseInstanceId.getInstance().getToken();
                if(fcmToken != null)
                Log.i("fcmToken",fcmToken) ;
            }
        });



        String fcmToken = FirebaseInstanceId.getInstance().getToken();

        if(fcmToken != null)
        Log.i("fcmToken",fcmToken) ;
//        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
//        edit.putBoolean("SENT-TOKEN", true);
//        edit.putString("token_number", fcmToken);
//        edit.apply();
//        edit.commit();
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//        String token = preferences.getString("token", "");

    }
}
