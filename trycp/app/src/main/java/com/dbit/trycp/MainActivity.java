package com.dbit.trycp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter arrayadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchContacts();
    }

    private void fetchContacts(){
      //  Log.i("@trycp","FetchContact start");
        ArrayList<String> contacts = new ArrayList<String>();


        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection ={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection=null;  //where clause
        String[] selectionArgs=null; //where condition
        String sortOrder=null;  //none
        ContentResolver resolver=getContentResolver();
        Cursor c = resolver.query(uri,projection,selection,selectionArgs,sortOrder);

        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contacts.add(name+"\n"+num);

            Log.i("@trycp","Name - "+ name +" Number - "+num);
        }

        arrayadapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,contacts);

        lv=findViewById(R.id.listcontacts);
        lv.setAdapter(arrayadapter);
    }
}
