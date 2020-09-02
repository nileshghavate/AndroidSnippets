package com.dbit.tryintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity {

    AppCompatButton b;
    EditText e;
    TextClock tc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b=findViewById(R.id.acb);
        e=findViewById(R.id.et_name);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    
                Bundle bundle = new Bundle();
                bundle.putInt("MyValue",3);
                bundle.putString("MyName",e.getText().toString());
                
                Bundle bundle2 = new Bundle();
                bundle2.putInt("MyValue",5);
                bundle2.putString("MyName",e.getText().toString());
                

                Intent i = new Intent(MainActivity.this,display.class);

                i.putExtra("v1",3);
                i.putExtra("v2",e.getText().toString());
                // i.putExtra("v1",5);
                // i.putExtra("v2",e.getText().toString());
                i.putExtras(bundle);
                i.putExtras(bundle2);
                startActivity(i);
            }
        });


    }
}
