package com.dbit.tryfirebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.InstanceIdResult;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        Log.i("ProfileActivity", "onCreate");

        //FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
          //  @Override
            //public void onComplete(@NonNull Task<InstanceIdResult> task) {
              //  if(task.isSuccessful())
                //{
                    //String token = task.getResult().getToken();
                    String token = FirebaseInstanceId.getInstance().getToken();
                    String email = mAuth.getCurrentUser().getEmail();
                    Log.i("ProfileActivity", "token"+token);
                    Log.i("ProfileActivity", "email"+email);
                    User u=new User(email,token);

                    DatabaseReference dbusers= FirebaseDatabase.getInstance().getReference("users");
                    DatabaseReference childuser= dbusers.child(mAuth.getCurrentUser().getUid());
                    childuser.setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ProfileActivity.this,"Token Saved",Toast.LENGTH_LONG).show();
                        }
                    });

                //}else{
                    //Toast.makeText(ProfileActivity.this,"Token NOT Saved",Toast.LENGTH_LONG).show();
                //}
            }
        //});
    //}

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()==null)
        {

            Intent intent = new Intent(this,MainActivity.class);
       //     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


        }
    }
}
