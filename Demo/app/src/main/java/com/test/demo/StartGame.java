package com.test.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StartGame extends AppCompatActivity implements View.OnClickListener{
    ImageView[] image;
    TextView tvscore;
    TextView tvtime;
    int score=0;
    int startTime=15;
    String[] Access={"boss","monster1","monster2","monster3","monster4","monster5","monster6","monster7"};
    AlertDialog.Builder builder;
    private Handler handler =new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        tvscore=(TextView)findViewById(R.id.textView2);
        handler.postDelayed(updataTimer, 1000);
        handler.postDelayed(random,2000);
        image=new ImageView[9];
        builder=new AlertDialog.Builder(this);
        for(int i=0;i<=8;i++){
            String name="imageView"+(i+1);
            image[i]=(ImageView)findViewById(getResources().getIdentifier(name,"id",getPackageName()));
            image[i].setOnClickListener(this);
            image[i].setImageResource(getResources().getIdentifier(Access[(int)(Math.random()*8)],"drawable",getPackageName()));
        }



    }

    @Override
    public void onClick(View v) {
        String show;
        ImageView newimage=(ImageView)v;
        if(newimage.getDrawable().getCurrent().getConstantState()==getResources().getDrawable(R.drawable.boss).getConstantState())
            score-=50;
        else
            score+=5;
        tvscore.setText("score:"+score);
    }
    public Runnable updataTimer=new Runnable() {
        @Override
        public void run() {
            tvtime = (TextView) findViewById(R.id.textView3);

            if (startTime >= 0) {
                tvtime.setText("time=" + startTime);
                handler.postDelayed(this, 1000);

            }else{
                handler.removeCallbacks(updataTimer);
                handler.removeCallbacks(random);

                for(int i=0;i<=8;i++){
                    String name="imageView"+(i+1);
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

    List<Integer> list=new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8));
    public Runnable random=new Runnable() {

        @Override
        public void run() {

            handler.postDelayed(this,2000);
            int num1 =(int)(Math.random()*8+1);

            for(ImageView i:image){
               // i.setVisibility(View.INVISIBLE);
            }
            for(int i=0;i<num1;i++){
                int num2 = list.get(i);
//            image[num2].setImageResource(getResources().getIdentifier(Access[(int)Math.random()*8],"id",getPackageName()));
                Animation am=new TranslateAnimation(image[num2].getX(),image[num2].getX(),image[num2].getY(),image[num2].getY()-((int)(Math.random()*36+15)));
                am.setDuration(2000);
                am.setRepeatCount(0);
                image[num2].setVisibility(View.VISIBLE);
                image[num2].setAnimation(am);
                am.startNow();


            }
        }
    };

}
