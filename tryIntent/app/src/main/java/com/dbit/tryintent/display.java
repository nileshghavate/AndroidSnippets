package com.dbit.tryintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

public class display extends AppCompatActivity {

    TextView t,tvd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        t = findViewById(R.id.tv_named);
        tvd = findViewById(R.id.tv_valued);



        Intent i = getIntent();
        int MyValue = i.getIntExtra("v1",0);
        String MyName = i.getStringExtra("v2");

         /*
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        int MyValue = bundle.getInt("MyValue");
        String MyName = bundle.getString("MyName");
        */

        t.setText(MyName);
        tvd.setText(""+MyValue);


    }
}
