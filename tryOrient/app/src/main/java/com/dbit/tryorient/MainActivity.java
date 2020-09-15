package com.dbit.tryorient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int score;

    AppCompatEditText et;
   String etString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et_name);
        score=0;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        etString=et.getText().toString();
        outState.putString("MyName",etString);
        outState.putInt("MyScore",score);

      }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        et.setText(savedInstanceState.getString("MyName"));
        score=savedInstanceState.getInt("MyScore");

    }

    public void performAction(View v){
        switch(v.getId()){

            case R.id.btn_incscore:
                    score +=1;
                break;

            case R.id.btn_showscore:
               Toast t= Toast.makeText(getApplicationContext(),"Score :"+score,Toast.LENGTH_LONG);
                t.show();
                break;
        }
    }
}
