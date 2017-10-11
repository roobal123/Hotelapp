package com.example.ottal.hotelmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {
    ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE); //hide title bar

       getSupportActionBar().hide();                      //to remove action bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        iv1 = (ImageView) findViewById(R.id.imageView);
       Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
        iv1.setAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                    // When animation will finish load main activity
                finish();
                 startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
      //  t1.start();
    }

 /*   Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                // Thread will sleep for 5 seconds
                Thread.sleep(5 * 1000);

                // After 5 seconds redirect to another intent
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);

                //Remove activity
                finish();

            } catch (Exception e) {

            }

        }
    }) {

    };*/


}
