package com.dbit.demointent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    AppCompatButton b1,b2,b3,b4,b5;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeResource();
        setClickListener();
    }


    public void initializeResource(){

        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b4=findViewById(R.id.button4);
        b5=findViewById(R.id.button5);

    }

    public void setClickListener(){


        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
    }


    public void onClick(View v){

        switch(v.getId()){

            case R.id.button :
                    i = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.co.in"));
                    startActivity(i);
                    break;

            case R.id.button2 :
                    i = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("tel:+919594813901"));
                    startActivity(i);
                    break;

            case R.id.button3 :
                    i=new Intent();
                    i.setAction(android.content.Intent.ACTION_VIEW);
                    i.setData(ContactsContract.Contacts.CONTENT_URI);
                    startActivity(i);
                    break;

            case R.id.button4 :
                    i = new Intent("com.dbit.demointent.CustomActivity.LAUNCH",
                    Uri.parse("https://www.google.co.in"));
                    startActivity(i);
                    break;

            case R.id.button5 :
                    Toast t= Toast.makeText(getApplicationContext(),"Opening 1",Toast.LENGTH_LONG);
                    t.show();
                    i = new Intent(this,sendEmail.class);
                    startActivity(i);
                    break;

        }



    }
}
