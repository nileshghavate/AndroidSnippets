package com.dbit.trybro;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Event !!!",Toast.LENGTH_LONG).show();

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);

        int state = tm.getCallState();

        if(state == TelephonyManager.CALL_STATE_OFFHOOK)
        {
            Toast.makeText(context,"Call Active !!!",Toast.LENGTH_LONG).show();
        }
        if(state == TelephonyManager.CALL_STATE_IDLE)
        {
            Toast.makeText(context,"No Call !!!",Toast.LENGTH_LONG).show();
        }
        if(state == TelephonyManager.CALL_STATE_RINGING)
        {
            Toast.makeText(context,"Ringing State !!!",Toast.LENGTH_LONG).show();
        }


    }
}
