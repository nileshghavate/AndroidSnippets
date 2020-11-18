package com.dbit.trydownload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

   private static final int PERMISSION_STORAGE_CODE =1000 ;
    private Button button;
    private long downloadID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.download);

        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // beginDownload();
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                    {
                        String[]  permissions ={Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_STORAGE_CODE);
                      //  beginDownload();
                    }
                    else
                    {
                        beginDownload();
                    }
                }


            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case PERMISSION_STORAGE_CODE : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        beginDownload();
                }
                else
                    Toast.makeText(this,"Permission Denied !!",Toast.LENGTH_LONG).show();
            }
        }
    }

    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

          //  Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();

            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(MainActivity.this, "Download Completed"+id, Toast.LENGTH_LONG).show();
            }

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
       
        unregisterReceiver(onDownloadComplete);
        
    }

    private void beginDownload(){

        String url="https://www.sample-videos.com/video123/mp4/720/big_buck_bunny_720p_10mb.MP4";
            //"http://speedtest.ftp.otenet.gr/files/test100Mb.db"

        File file=new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"tryFile");
        /*
        Create a DownloadManager.Request with all the information necessary to start the download
         */
        try 
        {
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));

        request.setTitle("MP4 File")// Title of the Download Notification
        .setDescription("Downloading...")// Description of the Download Notification
        .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI)
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)// Visibility of the download Notification
        .setDestinationUri(Uri.fromFile(file))// Uri of the destination file
        .setAllowedOverMetered(true)// Set if download is allowed on Mobile network
        .setAllowedOverRoaming(true);// Set if download is allowed on roaming network


        DownloadManager downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        downloadID = downloadManager.enqueue(request);// enqueue puts the download request in the queue.
        } 
        catch (Exception e) 
        {
        Toast.makeText(MainActivity.this, "Exception : "+e,Toast.LENGTH_LONG).show();
        }

        }

}
