package com.mohameddev.yo.Ui;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mohameddev.yo.R;
import com.mohameddev.yo.utils.utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText email,password;
    Button login;
    TextView new_account,forget_pass;
    String getemail,getpass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();


        idInstalization();
        login.setOnClickListener(this);
        new_account.setOnClickListener(this);
        forget_pass.setOnClickListener(this);




    }
    public void login(String email,String password){
        if (email.isEmpty()&&password.isEmpty()){
            Toast.makeText(getApplicationContext(),"enter data",Toast.LENGTH_SHORT).show();

        }
        else

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getApplicationContext(), "conecting.",
                                Toast.LENGTH_SHORT).show();

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "conected.",
                                    Toast.LENGTH_SHORT).show();

                            moveon((String.valueOf(user)));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "errour in server .",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


    public void idInstalization(){
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.bnt_login);
        new_account=findViewById(R.id.txt_signup_login);
        forget_pass=findViewById(R.id.txt_fo_pass);
    }
    public void getdata(){
        getemail=email.getText().toString();
        getpass= password.getText().toString();
        login(getemail,getpass);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth=FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null) {
            moveon(mAuth.getUid());
        }




        }
    public void moveon(String s){


        utils.intentWithClear(getApplicationContext(),MainActivity.class,"user_id",s);


    }
    public void signup(){
       utils.intentWithClear(getApplicationContext(),Signup.class,null,null);
    }
    public void setForget_pass(){
        utils.intentWithClear(getApplicationContext(),ForgotPass.class,null,null);
    }




    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bnt_login:
                getdata();
                break;
            case R.id.txt_signup_login:
                signup();
                break;
            case R.id.txt_fo_pass:
                setForget_pass();
                break;
        }

         }
     }
