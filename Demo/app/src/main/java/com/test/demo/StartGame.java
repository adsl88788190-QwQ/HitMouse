package com.test.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Statement;


public class StartGame extends AppCompatActivity implements View.OnClickListener{
    ImageView[] image;
    TextView tvscore;
    TextView tvtime;
    int score=0;
    int startTime=5;
    AlertDialog.Builder builder;
    private Handler handler =new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        tvscore=(TextView)findViewById(R.id.textView2);
        handler.postDelayed(updataTimer,1000);
        image=new ImageView[9];
        builder=new AlertDialog.Builder(this);
        for(int i=0;i<=8;i++){
            String name="imageView"+(i+1);
            image[i]=(ImageView)findViewById(getResources().getIdentifier(name,"id",getPackageName()));
            image[i].setOnClickListener(this);

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
                dialog.show();


            }
            startTime -= 1;
        }
    };


}
