package com.test.demo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;


public class StartGame extends AppCompatActivity {
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
        tvtime = (TextView) findViewById(R.id.textView3);
        //Rand Num
        Randlist();
        //Set Anination
        AnimationSetting();
        //Set ImageView
        image = new ImageView[9];
        for (int i = 0; i <= 8; i++) {
            String name = "imageView" + (i + 1);
            image[i] = (ImageView) findViewById(getResources().getIdentifier(name, "id", getPackageName()));
            image[i].setOnClickListener(ImageClick);
            image[i].setAnimation(am);
        }
        //Start Time & Start
        handler.postDelayed(updataTimer, 1000);
    }

    public Runnable updataTimer = new Runnable() {
        @Override
        public void run() {
            //tvtime.setText();
            tvscore.setText("Score : "+score+"time : " + startTime);

            if (startTime >= 0) {
                handler.post(random);
                handler.postDelayed(this, 1000);

            } else {
                handler.removeCallbacks(updataTimer);
                handler.removeCallbacks(random);

                for (int i = 0; i <= 8; i++) {
                    image[i].setOnClickListener(null);
                }
                builder = new AlertDialog.Builder(StartGame.this);
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
    //一開始產生好亂數表
    int[] randmap;
    int randnum = 0;
    public void Randlist(){
        randmap = new int[90];
        for(int i = 0;i<randmap.length;i++)
            randmap[i] = (int)(Math.random()*8);

    }
    //動畫需求
    Animation am;
    public void AnimationSetting(){
        //Animation
        am = new TranslateAnimation(0, 0, 0, 0 - ((int) (Math.random() * 36 + 15)));//注意
        am.setDuration(1000);
        am.setRepeatCount(0);
    }
    // 取得圖片
    public static Bitmap readBitMap(Context context, int resId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is,null,opt);
    }

    public View.OnClickListener ImageClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final ImageView newimage = (ImageView) view;
            if (newimage.getDrawable().getCurrent().getConstantState() == getResources().getDrawable(R.mipmap.boss).getConstantState()) {
                score -= 50;
            } else {
                score += 5;
            }
            newimage.setImageBitmap(readBitMap(StartGame.this,R.mipmap.boom));
        }
    };
    public Runnable random = new Runnable() {
        @Override
        public void run() {
            int num1 = ((int) (Math.random() * 4))+1;//一次出現1~5個地鼠
            for (ImageView i : image)  //讓每張圖都看不見
                i.setVisibility(View.INVISIBLE);
            for (int i = 0; i < num1; i++) {
                int resid = getResources().getIdentifier(Access[(int) (Math.random() * 8)], "mipmap", getPackageName());
                image[randmap[randnum + i]].setImageResource(resid);
                image[randmap[randnum+i]].setVisibility(View.VISIBLE);
            }
            randnum+=num1;
        }
    };
}
