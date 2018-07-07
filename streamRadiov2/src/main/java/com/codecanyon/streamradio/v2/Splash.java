package com.codecanyon.streamradio.v2;

import com.example.flashlight.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class Splash extends Activity{

     MediaPlayer mysong;


    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.splash);
        mysong=MediaPlayer.create(Splash.this,R.raw.music);
        mysong.start();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(Splash.this,Welcome.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    public void move(View view){
        ImageView image = (ImageView)findViewById(R.id.mus);
        ImageView imagea = (ImageView)findViewById(R.id.musa);
        ImageView imageb = (ImageView)findViewById(R.id.musb);
        ImageView imagec = (ImageView)findViewById(R.id.musc);
        ImageView imaged = (ImageView)findViewById(R.id.musd);
        ImageView imagee = (ImageView)findViewById(R.id.muse);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movea);
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.moveb);
        image.startAnimation(animation1);
        imagea.startAnimation(animation1);
        imageb.setAnimation(animation1);
        imaged.setAnimation(animation2);
        imagec.setAnimation(animation1);
        imagee.setAnimation(animation3);

    }
    protected void onPause(){
        super.onPause();
        mysong.release();
        finish();
    }

}

