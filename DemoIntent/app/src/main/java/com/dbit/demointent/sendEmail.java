package com.dbit.demointent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class sendEmail extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        AppCompatButton btnSend = findViewById(R.id.btn_email);

        btnSend.setOnClickListener(this);
    }

    public void onClick(View v) {
        Toast t= Toast.makeText(getApplicationContext(),"Opening",Toast.LENGTH_LONG);
        t.show();
        Intent si = new Intent(Intent.ACTION_SEND);
        si.setType("message/rfc822");
        si.putExtra(Intent.EXTRA_EMAIL, new String[]{"admin@dbit.in"});
        si.putExtra(Intent.EXTRA_SUBJECT, "Welcome to DBIT");
        si.putExtra(Intent.EXTRA_TEXT, "Hi, When is College Reopening ?");
        startActivity(Intent.createChooser(si,"Choose Mail App"));
    }
}
