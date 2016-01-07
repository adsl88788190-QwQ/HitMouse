package com.test.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button start;
    Button change;
    Button score;
    Button share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button)findViewById(R.id.Start);
        change=(Button)findViewById(R.id.Change);
        score=(Button)findViewById(R.id.Score);
        share=(Button)findViewById(R.id.primary_button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ChooseList.class));
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ChangeWeapon.class));
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ScoreList.class));
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             shareTo("score","hihihih","send");
            }
        });
        SharedPreferences setting =getSharedPreferences("weapan",0);
        int s=setting.getInt("weapan",0);
        Toast.makeText(MainActivity.this,s+"",Toast.LENGTH_SHORT).show();

    }
    private void shareTo(String subject, String body, String chooserTitle) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);

        startActivity(Intent.createChooser(sharingIntent, chooserTitle));
    }
}
