package com.example.tjswh.intentassgnmt;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.w3c.dom.Text;

public class JK3Activity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{

    public final static String VIDEO_URL = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
    VideoView vv;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jk3);

        Button backtoM = (Button) findViewById(R.id.button8);
        SearchView v1 = (SearchView) findViewById(R.id.v1);
        ImageView p1 = (ImageView) findViewById(R.id.profile1);
        TextView n1 = (TextView) findViewById(R.id.nickname);
        ImageView img = (ImageView) findViewById(R.id.img);
        RatingBar r1 = (RatingBar) findViewById(R.id.rating1);
        ImageView p2 = (ImageView) findViewById(R.id.profile2);
        TextView n2 = (TextView) findViewById(R.id.nickname2);
        vv = (VideoView) findViewById(R.id.vv);
        RatingBar r2 = (RatingBar) findViewById(R.id.rating2);
        CalendarView cv = (CalendarView) findViewById(R.id.cv);

        backtoM.setOnClickListener(this);
        v1.setOnClickListener(this);
        p1.setOnClickListener(this);
        n1.setOnClickListener(this);
        img.setOnClickListener(this);
        r1.setOnTouchListener(this);
        p2.setOnClickListener(this);
        n2.setOnClickListener(this);
        vv.setOnTouchListener(this);
        r2.setOnTouchListener(this);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), year+"/"+(month+1)+"/"+dayOfMonth, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void GoToMain(View v) {
        Intent Mintent = new Intent(JK3Activity.this, MainActivity.class);
        setResult(RESULT_OK, Mintent);
        finish();
    }

    @Override
    public void onClick(View v){
        switch(v.getId())
        {
            case R.id.button8:
                GoToMain(v);
                break;
            case R.id.v1:
                Toast.makeText(getApplicationContext(), "검색뷰를 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.profile1:
                Toast.makeText(getApplicationContext(), "profile1을 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.nickname:
                Toast.makeText(getApplicationContext(), "nickname을 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.img:
                Toast.makeText(getApplicationContext(), "이미지 뷰를 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.profile2:
                Toast.makeText(getApplicationContext(), "profile2를 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.nickname2:
                Toast.makeText(getApplicationContext(), "nickname2를 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
           }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            switch(v.getId())
            {
                case R.id.rating1:
                    Toast.makeText(getApplicationContext(), "rating1을 눌렀습니다", Toast.LENGTH_LONG).show();
                    break;
                case R.id.rating2:
                    Toast.makeText(getApplicationContext(), "rating2를 눌렀습니다.", Toast.LENGTH_LONG).show();
                    break;
                case R.id.vv:
                    Toast.makeText(getApplicationContext(), "비디오 뷰를 눌렀습니다.", Toast.LENGTH_LONG).show();
                    break;
            }
        }
        return false;
    }
}
