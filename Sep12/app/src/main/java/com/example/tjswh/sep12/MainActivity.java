package com.example.tjswh.sep12;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity  {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.Button1);
        CheckBox chk = (CheckBox) findViewById(R.id.checkBox);
        RadioButton rbtn1 = (RadioButton) findViewById(R.id.radioButton1);
        RadioButton rbtn2 = (RadioButton) findViewById(R.id.radioButton2);
        ToggleButton tbtn = (ToggleButton) findViewById(R.id.toggleButton);
        Switch sch = (Switch) findViewById(R.id.switch1);
        RatingBar rbar = (RatingBar) findViewById(R.id.ratingBar);
        ProgressBar pbar1 = (ProgressBar) findViewById(R.id.progressBar1);
        ProgressBar pbar2 = (ProgressBar) findViewById(R.id.progressBar2);
        SeekBar sbar = (SeekBar) findViewById(R.id.seekBar);
        Button btn2 = (Button) findViewById(R.id.Button2);

        btn1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v){
                select(v);
            }
        });

        chk.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v);
            }
        });

        rbtn1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v);
            }
        });

        rbtn2.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v);
            }
        });

        tbtn.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v);
            }
        });

        sch.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v);
            }
        });

        rbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    select(v);
                }
                return true;
            }
        });
        rbar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                select(v);
            }
        });

        pbar1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v);
            }
        });

        pbar2.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v);
            }
        });

        sbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    select(v);
                }
                return true;
            }
        });
        sbar.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v);
            }
        });

        btn2.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v);
            }
        });
    }


    public void select(View v){
        switch(v.getId())
        {
            case R.id.Button1:
                Toast.makeText(getApplicationContext(), "오렌지를 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.checkBox:
                Toast.makeText(getApplicationContext(), "체크를 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.radioButton1:
                Toast.makeText(getApplicationContext(), "첫번째 라디오를 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.radioButton2:
                Toast.makeText(getApplicationContext(), "두번째 라디오를 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.toggleButton:
                Toast.makeText(getApplicationContext(), "토글버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.switch1:
                Toast.makeText(getApplicationContext(), "스위치를 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.ratingBar:
                Toast.makeText(getApplicationContext(), "평가를 설정했습니다", Toast.LENGTH_LONG).show();
                break;
            case R.id.progressBar1:
                Toast.makeText(getApplicationContext(), "progressbar1을 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.progressBar2:
                Toast.makeText(getApplicationContext(), "progressbar2를 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.seekBar:
                Toast.makeText(getApplicationContext(), "seekbar를 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.Button2:
                Toast.makeText(getApplicationContext(), "마지막 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
