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

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText e1, e2;
    Button b;
    FirebaseAuth lauth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //get current user


        e1 = (EditText) findViewById(R.id.editText3);
        e2 = (EditText) findViewById(R.id.editText4);
        b = (Button) findViewById(R.id.login);
        lauth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        b.setOnClickListener(this);

    }

    public void login() {

        String email = e1.getText().toString();
        String pass = e2.getText().toString();

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "enter email and password", Toast.LENGTH_SHORT).show();
            return;

        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "enter email ", Toast.LENGTH_SHORT).show();
            return;

        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "enter password", Toast.LENGTH_SHORT).show();
            return;

        }

        dialog.setMessage("Logging in..");
        dialog.show();
        lauth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (task.isSuccessful()) {
                            // there was an error
                            startActivity(new Intent(getApplicationContext(),Login_After.class));
                            Toast.makeText(getApplicationContext(), "successfully logged in", Toast.LENGTH_LONG).show();
                            e1.setText(" ");
                            e2.setText(" ");
                            dialog.cancel();

                        } else if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Access Denied", Toast.LENGTH_LONG).show();
                            dialog.cancel();
                        }
                    }
                });

    }


    @Override
    public void onClick(View view) {
        if (view == b) {
            login();
        }
    }
}