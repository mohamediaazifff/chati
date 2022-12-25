package com.mohameddev.yo.Ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mohameddev.yo.R;

import java.util.HashMap;

public class Signup extends AppCompatActivity  implements View.OnClickListener {

    FirebaseAuth firebaseAuth;

    DatabaseReference reference;
    MaterialButton sign_up;
    TextView login;
    EditText email,password,username;
    String getemail,getpass,getusername;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
instalizationid();
        firebaseAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(this);
        sign_up.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);


    }



    public void setSign_up(String email, String password, String username){

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(),"Please enter email...",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(getApplicationContext(),"Please enter password...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(username)){
            Toast.makeText(getApplicationContext(),"Please enter username...",Toast.LENGTH_SHORT).show();

        }


        else {
            progressDialog.setTitle("Creating New Account");
            progressDialog.setMessage("hey wait we are building it...");
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();

            register(email,password,username);
        }

    }

    private void register(String email, String password, String username) {
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {



                if (task.isSuccessful()){
                    FirebaseUser FirebaseUser = firebaseAuth.getCurrentUser();
                    assert FirebaseUser != null;
                    String userid = FirebaseUser.getUid();
                    reference = FirebaseDatabase.getInstance().getReference();

                            reference = FirebaseDatabase.getInstance().getReference("users").child(userid);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id",userid);
                    hashMap.put("username",username);
                    hashMap.put("user_profile","default");
                    hashMap.put("status","online");
                    hashMap.put("show_status","true");
                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>(){
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                               moveOn();
                            }
                        }
                    });
                }

                else {
                    progressDialog.dismiss();
                    Toast.makeText(Signup.this, "Email already exists!", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }

    public void instalizationid(){
        sign_up=(MaterialButton) findViewById(R.id.btnsignup);
        login=(TextView) findViewById(R.id.txt_login_signup);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        username=(EditText) findViewById(R.id.username);



    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsignup:
                getdata();

                break;
            case R.id.txt_login_signup:
                Login();
                break;
        }

    }


    public void moveOn(){
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null) {
            moveOn();
        }}
    public void Login(){
        Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void getdata(){
        getemail=email.getText().toString();
        getpass= password.getText().toString();
        getusername= username.getText().toString();
        setSign_up(getemail,getpass,getusername);
    }
}
