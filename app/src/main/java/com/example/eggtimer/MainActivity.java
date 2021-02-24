package com.example.eggtimer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView timerTextView;
    ImageView imageView;
    Button button;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        //timerTextView.setText("0:00");
        //timerSeekBar.setProgress(0);
        //countDownTimer.cancel();
        //button.setText("Go");
        //imageView.setImageResource(R.drawable.egg);
        timerSeekBar.setEnabled(true);
        counterIsActive=false;
        Intent intent = getIntent();
        finish();
        startActivity(intent);


    }


    public void controlTimer(View view){
        if(counterIsActive==false) {
            //imageView.setImageResource(R.drawable.egg);
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            button.setText("Reset");
            countDownTimer=new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    int seconds = (int) millisUntilFinished / 1000;
                    int minutes = (int) seconds / 60;
                    if (seconds < 10) {
                        timerTextView.setText(Integer.toString(minutes) + ":0" + Integer.toString(seconds));
                    } else {
                        timerTextView.setText(Integer.toString(minutes) + ":" + Integer.toString(seconds));

                    }

                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0:00");
                    imageView.setImageResource(R.drawable.broken_egg);
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ship_bell);
                    mediaPlayer.start();
                    //resetTimer();


                    //img.setImageResource(R.drawable.broken_egg);

                }
            }.start();
        }
        else{
            resetTimer();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#696969"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        timerTextView = (TextView)findViewById(R.id.timerTextView);
        timerSeekBar = (SeekBar)findViewById(R.id.timerSeekBar);
        button=(Button)findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.timerImageView);


        timerSeekBar.getProgressDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        timerSeekBar.getThumb().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);


        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(0);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //timerTextView.setText(Integer.toString(minutes) + ":"+Integer.toString(seconds));
                int minutes = (int)progress/60;
                int seconds = progress - minutes*60;
                if(seconds<10){
                    timerTextView.setText(Integer.toString(minutes) + ":0"+Integer.toString(seconds));


                }
                else{
                    timerTextView.setText(Integer.toString(minutes) + ":"+Integer.toString(seconds));

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //seekBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //seekBar.setVisibility(View.GONE);

            }
        });
    }
}