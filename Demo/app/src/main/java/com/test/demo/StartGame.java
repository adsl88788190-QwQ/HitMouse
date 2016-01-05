package com.test.demo;

import android.app.AlertDialog;
import android.app.usage.UsageEvents;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StartGame extends AppCompatActivity implements View.OnClickListener {
    ImageView[] image;
    TextView tvscore;
    TextView tvtime;
    ImageView viewtouch;
    RelativeLayout Rl;
    int num2;
    int score = 0;
    int startTime = 20;
    String[] Access = {"boss", "monster1", "monster2", "monster3", "monster4", "monster5", "monster6", "monster7", "boom"};
    AlertDialog.Builder builder;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        viewtouch = (ImageView) findViewById(R.id.imageView10);
        tvscore = (TextView) findViewById(R.id.textView2);
//        Rl = (RelativeLayout) findViewById(R.id.Rlayout);

//        handler.postDelayed(updataTimer, 1000);
//        handler.postDelayed(random, 1000);
        image = new ImageView[9];
        builder = new AlertDialog.Builder(this);
        for (int i = 0; i <= 8; i++) {
            String name = "imageView" + (i + 1);
            image[i] = (ImageView) findViewById(getResources().getIdentifier(name, "id", getPackageName()));
            image[i].setOnClickListener(this);
//            image[i].setOnTouchListener(onTouchListener);
        }


    }

    @Override
    public void onClick(View v) {

        final ImageView newimage = (ImageView) v;
        if (newimage.getDrawable().getCurrent().getConstantState() == getResources().getDrawable(R.drawable.boss).getConstantState()) {

            score -= 50;
        } else {

            score += 5;
        }
        newimage.clearAnimation();
//        newimage.setImageDrawable(getResources().getDrawable(R.drawable.boom));
        tvscore.setText("score:" + score);

    }



    public Runnable updataTimer = new Runnable() {
        @Override
        public void run() {
            tvtime = (TextView) findViewById(R.id.textView3);

            if (startTime >= 0) {
                tvtime.setText("time=" + startTime);
                handler.postDelayed(this, 1000);

            } else {
                handler.removeCallbacks(updataTimer);
                handler.removeCallbacks(random);

                for (int i = 0; i <= 8; i++) {
//                    String name="imageView"+(i+1);
                    image[i].setOnClickListener(null);
                }
                builder.setTitle("時間到了!");
                builder.setMessage("恭喜你得到" + score + "分");
                builder.setPositiveButton("繼續遊戲", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(StartGame.this, StartGame.class));
                    }
                });
                builder.setNegativeButton("結束遊戲", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(StartGame.this, MainActivity.class));
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();


            }
            startTime -= 1;
        }
    };


    public Runnable random = new Runnable() {

        @Override
        public void run() {

            handler.postDelayed(this, 2000);
            int num1 = (int) (Math.random() * 5);//一次出現0~5個地鼠

            for (ImageView i : image) { //讓每張圖都看不見
                i.setVisibility(View.INVISIBLE);
            }
            for (int i = 0; i < num1; i++) {
                num2 = (int) (Math.random() * 9);
                image[num2].setImageResource(getResources().getIdentifier(Access[(int) (Math.random() * 8)], "drawable", getPackageName()));
//            image[num2].setImageResource(getResources().getIdentifier(Access[(int)Math.random()*8],"id",getPackageName()));
//                int[] test =new int[2];
//                image[num2].getLocationOnScreen(test);
//                Animation am=new TranslateAnimation(image[num2].getX(),image[num2].getX(),image[num2].getY()-50,image[num2].getY()-((int)(Math.random()*36+15)));
                Animation am = new TranslateAnimation(0, 0, 0, 0 - ((int) (Math.random() * 36 + 15)));//注意
                am.setDuration(2000);
                am.setRepeatCount(0);
                image[num2].setVisibility(View.VISIBLE);
                image[num2].setAnimation(am);
                am.startNow();

            }
        }
    };

//    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//
//           Toast.makeText(StartGame.this,event.getX()+" "+event.getY(),Toast.LENGTH_SHORT).show();
//                viewtouch.setImageResource(R.drawable.weapan);
//                viewtouch.setVisibility(View.VISIBLE);
//                viewtouch.setX(v.getX()-50);
//                viewtouch.setY(v.getY()-50);
//            }
//            return false;
//        }
//    };

}
