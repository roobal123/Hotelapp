package com.example.ottal.hotelmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText email,password;
    Button b;

    ProgressDialog progress;
    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }
            }
        };

        email= (EditText) findViewById(R.id.editText);
        password= (EditText) findViewById(R.id.editText2);
        b= (Button) findViewById(R.id.button2);

        progress= new ProgressDialog(this);


        auth=FirebaseAuth.getInstance();
        b.setOnClickListener(this);



    }

    public void register(){

        String mail=email.getText().toString().trim();
        String pass=password.getText().toString().trim();

        if(TextUtils.isEmpty(mail) && TextUtils.isEmpty(pass) ){
            Toast.makeText(this,"please enter email and password", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(mail)){
            Toast.makeText(this,"please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        if(pass.length()<6){
            Toast.makeText(this,"password too short..",Toast.LENGTH_LONG).show();
            return;
        }

        progress.setMessage("Registering..please wait !!");
        progress.show();



        auth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    progress.cancel();
                    Toast.makeText(getApplicationContext(),"Registered successfully",Toast.LENGTH_SHORT).show();

                }
                else{
                    progress.cancel();
                    Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();


                }

            }
        });


    }



    @Override
    public void onClick(View view) {

        if(view==b){
            register();
        }



    }
}
