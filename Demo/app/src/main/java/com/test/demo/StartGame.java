package com.test.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StartGame extends AppCompatActivity implements View.OnClickListener{
    ImageView[] image;
    TextView tvscore;
    int score=0;

//    int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        tvscore=(TextView)findViewById(R.id.textView2);
        image=new ImageView[9];
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
}
