package com.dbit.trysq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private static final int DATABASE_VERSION = 2;
    EditText et_user,et_pass;
    Button bt_login,bt_reg;

    DBHelper dbHelp;
    Cursor cur=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toast.makeText(this,"LOGIN: ON CREATE",Toast.LENGTH_SHORT).show();

        dbHelp=new DBHelper(this,"csi",null,DATABASE_VERSION);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"LOGIN: ON START",Toast.LENGTH_SHORT).show();

        et_user= (EditText) findViewById(R.id.editText);
        et_pass= (EditText) findViewById(R.id.editText2);
        bt_login= (Button) findViewById(R.id.btn_login);
        bt_reg=(Button) findViewById(R.id.btn_reg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"LOGIN: ON RESUME",Toast.LENGTH_SHORT).show();


        myListner();


        bt_login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {


                SQLiteDatabase db=dbHelp.getWritableDatabase();

                cur=db.rawQuery("select * from login where username='"+et_user.getText().toString()+"'",null);



                if(cur!=null && cur.getCount()>0) {
                    cur.moveToFirst();

                    if(et_pass.getText().toString().equals(cur.getString(1))) {
                        System.out.println(cur.getString(1));
                        Toast.makeText(getApplicationContext(), " AUTHENTIC", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"NOT AUTHENTIC",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"NOT REGISTERED",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }




    public void myListner()
    {
        bt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj=new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(obj);



            }
        });
    }
}
