package com.dbit.tryfirebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class MainActivity extends AppCompatActivity {

    private EditText etEmail,etPwd;
    private ProgressBar progressbar;
    private Button btSignUp;
    private CheckBox checkBoxRememberMe;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressbar = findViewById(R.id.progressbar);
        progressbar.setVisibility(View.INVISIBLE);
        etEmail = findViewById(R.id.et_email);
        etPwd = findViewById(R.id.et_pwd);
        btSignUp = findViewById(R.id.btn_signup);

        checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBoxRememberMe);
        PrefManager pm = new PrefManager(MainActivity.this);
        if( !pm.getEmail().isEmpty())
        {
            etEmail.setText(pm.getEmail());

        }
        if(!pm.getPwd().isEmpty())
        {
            etPwd.setText(pm.getPwd());
        }


        mAuth = FirebaseAuth.getInstance();
        // This is Firebase Auth Instance Object
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              createUser();
            }
        });
    }

    private void createUser()
    {
        final String email = etEmail.getText().toString().trim();
        final String pwd = etPwd.getText().toString().trim();


        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    //  to another activity let say profile activity
                    savePref( email, pwd);
                        startProfileActivity();
                }
                else{

                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        userLogin(email,pwd);
                        savePref( email, pwd);
                    }
                    else
                    {
                        progressbar.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_LONG);
                    }

                }

            }
        });

    }

    private void savePref(String email, String pwd){
        if (checkBoxRememberMe.isChecked())
            new PrefManager(MainActivity.this).saveLoginDetails(email, pwd);
    }

    private void userLogin(String email, String pwd)
    {
    mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                startProfileActivity();
            }
            else
            {
                progressbar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG);
            }
        }
    });
    }

    private void startProfileActivity(){
    Intent intent = new Intent(this,ProfileActivity.class);
    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
    }
}
