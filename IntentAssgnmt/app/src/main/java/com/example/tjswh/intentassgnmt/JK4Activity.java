package com.example.tjswh.intentassgnmt;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class JK4Activity extends AppCompatActivity implements View.OnClickListener {

    ViewPager vp;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jk4);

        Button backToM = (Button) findViewById(R.id.button9);
        vp = (ViewPager) findViewById(R.id.viewPager3);
        Button fbtn = (Button) findViewById(R.id.first);
        Button sbtn = (Button) findViewById(R.id.second);
        Button tbtn = (Button) findViewById(R.id.third);

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        backToM.setOnClickListener(this);
        fbtn.setOnClickListener(movePageListener);
        fbtn.setTag(0);
        sbtn.setOnClickListener(movePageListener);
        sbtn.setTag(1);
        tbtn.setOnClickListener(movePageListener);
        tbtn.setTag(2);
        vp.setOnClickListener(this);
    }

    View.OnClickListener movePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            vp.setCurrentItem(tag);
        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Toast.makeText(getApplicationContext(), "첫번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    return new FirstFragment();
                case 1:
                    Toast.makeText(getApplicationContext(), "두번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    return new SecondFragment();
                case 2:
                    Toast.makeText(getApplicationContext(), "세번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    return new ThirdFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void GoToMain(View v) {
        Intent Mintent = new Intent(JK4Activity.this, MainActivity.class);
        setResult(RESULT_OK, Mintent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button9:
                GoToMain(v);
                break;
            case R.id.viewPager3:
                Toast.makeText(getApplicationContext(), "viewPager 화면을 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
        }
    }
}