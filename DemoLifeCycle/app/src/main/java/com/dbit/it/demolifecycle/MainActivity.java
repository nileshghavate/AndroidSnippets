package com.dbit.it.demolifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast t = Toast.makeText(getApplicationContext(),"Nilesh:MainActivity : onCreate",Toast.LENGTH_LONG);
        t.show();

        Log.i("Nilesh","MainActivity : onCreate");

        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast t = Toast.makeText(getApplicationContext(),"Nilesh:MainActivity : onStart",Toast.LENGTH_LONG);
        t.show();

        Log.i("Nilesh","MainActivity : onStart");
    }


    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast t = Toast.makeText(getApplicationContext(),"Nilesh:MainActivity : onPause",Toast.LENGTH_LONG);
        t.show();

        Log.i("Nilesh","MainActivity : onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast t = Toast.makeText(getApplicationContext(),"Nilesh:MainActivity : onStop",Toast.LENGTH_LONG);
        t.show();

        Log.i("Nilesh","MainActivity : onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast t = Toast.makeText(getApplicationContext(),"Nilesh:MainActivity : onDestroy",Toast.LENGTH_LONG);
        t.show();

        Log.i("Nilesh","MainActivity : onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast t = Toast.makeText(getApplicationContext(),"Nilesh:MainActivity : onRestart",Toast.LENGTH_LONG);
        t.show();

        Log.i("Nilesh","MainActivity : onRestart");

    }
}