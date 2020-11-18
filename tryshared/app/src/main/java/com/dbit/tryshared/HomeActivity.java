package com.dbit.tryshared;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {
    Context context;
    TextView mEmailView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);
        this.context = context;

        SharedPreferences editor = context.getSharedPreferences("LoginDetails" ,Context.MODE_PRIVATE);
        String email  =editor.getString("Email", "default_value");

        mEmailView = (TextView) findViewById(R.id.textViewUser);

        mEmailView.setText(email);

    }

}
