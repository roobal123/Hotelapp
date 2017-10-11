
package com.example.ottal.hotelmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button b,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b= (Button) findViewById(R.id.login);
        b2= (Button) findViewById(R.id.register);
        b.setOnClickListener(this);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
            {
                startActivity(new Intent(MainActivity.this,Login.class));
                break;
            }
            case  R.id.register:
            {
                startActivity(new Intent(MainActivity.this,Register.class));
                break;
            }

        }
    }
}
