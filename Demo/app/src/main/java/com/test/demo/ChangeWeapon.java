package com.test.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class ChangeWeapon extends AppCompatActivity {

    private ViewPager viewPager;
    SharedPreferences setting;
    Button bt;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_weapon);
        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        setting=getSharedPreferences("weapan",MODE_PRIVATE);
        bt = (Button) findViewById(R.id.button);
        LayoutInflater li = LayoutInflater.from(this);
        final String weapan[]={"weapan1","weapan2","weapan3","weapan4","weapan5","weapan6"};

        //new 一個ArrayList<view> 來存放每個Page
        final ArrayList<View> arrayView = new ArrayList<>();
        arrayView.add(li.inflate(R.layout.a, null));
        arrayView.add(li.inflate(R.layout.b, null));
        arrayView.add(li.inflate(R.layout.c, null));
        arrayView.add(li.inflate(R.layout.d, null));
        arrayView.add(li.inflate(R.layout.e, null));
        arrayView.add(li.inflate(R.layout.f, null));

        //new 一個ArrayList 來放每個Page 的 Title
        final ArrayList<String> titleArray = new ArrayList<>();
        titleArray.add("Page1");
        titleArray.add("Page2");
        titleArray.add("Page3");
        titleArray.add("Page4");
        titleArray.add("Page5");
        titleArray.add("Page6");



        PagerAdapter apdter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return arrayView.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                ((ViewPager) container).removeView(arrayView.get(position));
            }

            @Override
            public CharSequence getPageTitle(int position) {
                // TODO Auto-generated method stub

                return titleArray.get(position);//這裡需回傳Title的名稱,position就是每個Page的index
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                ((ViewPager) container).addView(arrayView.get(position), 0);
                //((ViewPager)container).addView(arrayView.get(position));
                number =position+1;
                return arrayView.get(position);
            }

        };

        viewPager.setAdapter(apdter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {//監聽當ViewPager被改變Page時

            @Override
            public void onPageSelected(int arg0) {

            }

            @Override
            public void onPageScrolled(int index, float arg1, int arg2) {


            }

            @Override
            public void onPageScrollStateChanged(int index) {


            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resid = getResources().getIdentifier(weapan[number],"drawable",getPackageName());
                setting.edit().putInt("weapan",resid).commit();
                startActivity(new Intent().setClass( ChangeWeapon.this,MainActivity.class));
            }
        });
    }
}