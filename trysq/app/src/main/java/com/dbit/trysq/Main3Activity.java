package com.dbit.trysq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

     private static final int DATABASE_VERSION = 2;
    EditText edt_user,edt_pass;
    Button btn_reg;

    DBHelper dbHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main3);

        edt_user=(EditText) findViewById(R.id.edt_usr);
        edt_pass= (EditText) findViewById(R.id.edt_pass);
        btn_reg=(Button) findViewById(R.id.btn_reg);

        myRegListener();
    }

    public void myRegListener()
    {
        btn_reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                dbHelp=new DBHelper(getApplicationContext(),"csi",null,DATABASE_VERSION);
                SQLiteDatabase db=dbHelp.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("username", edt_user.getText().toString());
                values.put("password", edt_pass.getText().toString());
                //values.put("role","1");

                // Inserting Row
                db.insert("login", null, values);
                Toast.makeText(getApplicationContext(),"REGISTRED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
