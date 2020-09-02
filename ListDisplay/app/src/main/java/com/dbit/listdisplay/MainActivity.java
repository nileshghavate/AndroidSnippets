package com.dbit.listdisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    // Array of strings...
    String[] subjectArray = {"Enterprise Network Design","Infrastructure Security",
            "Artificial Intelligence","Soft Computing", "Mobile Application Development",
            "Network Design Lab","Advanced Security Lab","Intelligence System Lab",
            "Android Apps Development Lab","Project-I"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, subjectArray);

        ListView listView = (ListView) findViewById(R.id.subject_list);
        listView.setAdapter(adapter);

    }
}
