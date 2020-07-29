package com.example.C19.Auxilium;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Onetimescreen extends AppCompatActivity {
    Button b1;
    final Animation animation = new AlphaAnimation((float) 0.8, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onetimescreen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        /*ImageView im_blink = (ImageView) findViewById(R.id.im_blink);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        im_blink.startAnimation(animation);*/


        new Handler().postDelayed(new Runnable() {


            @Override

            public void run() {


                Intent intent = new Intent(Onetimescreen.this, Factivity.class);
                startActivity(intent);


                finish();

            }

        }, 5 * 1000); // wait for 5 seconds
    }
}
