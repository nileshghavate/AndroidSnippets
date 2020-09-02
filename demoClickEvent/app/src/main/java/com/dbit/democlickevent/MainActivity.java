package com.dbit.democlickevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton b;
    private AppCompatTextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b=findViewById(R.id.btn_clickhere);
        b.setOnClickListener(this);

        tv=findViewById(R.id.vw_label_1);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_clickhere:

                Toast t= Toast.makeText(this,"Button Clicked",Toast.LENGTH_LONG);
                t.show();

                tv.setText("Welcome");
                
                break;


            }
      
    }




}
