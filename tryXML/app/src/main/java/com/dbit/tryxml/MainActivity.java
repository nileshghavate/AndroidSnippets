package com.dbit.tryxml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private Object Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        List<Employee> employees = null;

        try {
            Log.i(""+getApplicationContext(),"XML Parser ");

            XMLPullParserHandler parser = new XMLPullParserHandler();
            employees = parser.parse(getAssets().open("employees.xml"));
            ArrayAdapter<Employee> adapter =
                    new ArrayAdapter<Employee>(this,R.layout.list_item, employees);
            listView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            // Inflate the menu; this adds items to the action bar if it is present.
//            //getMenuInflater().inflate(R.menu.main, menu);
//            return true;
//        }
    }
}
