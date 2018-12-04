package com.example.tjswh.intentassgnmt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class JK1Activity extends AppCompatActivity implements View.OnClickListener{

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private TextView text7;
    private RadioGroup grGroup;
    private RadioButton gr1;
    private RadioButton gr2;
    private RadioButton gr3;
    private RadioButton gr4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jk1);

        Button backBtn = (Button) findViewById(R.id.button6);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        text7 = (TextView) findViewById(R.id.text7);
        grGroup = (RadioGroup) findViewById(R.id.radioGroup);
        gr1 = (RadioButton) findViewById(R.id.gr1);
        gr2 = (RadioButton) findViewById(R.id.gr2);
        gr3 = (RadioButton) findViewById(R.id.gr3);
        gr4 = (RadioButton) findViewById(R.id.gr4);
//        TextView text8 = (TextView) findViewById(R.id.text8);

        backBtn.setOnClickListener(this);
        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
        text4.setOnClickListener(this);
        text5.setOnClickListener(this);
        text6.setOnClickListener(this);
        text7.setOnClickListener(this);
        gr1.setOnClickListener(this);
        gr2.setOnClickListener(this);
        gr3.setOnClickListener(this);
        gr4.setOnClickListener(this);
//        text8.setOnClickListener(this);
    }

    public void GoToMain(View v){
        String gr = "";
        if(grGroup.getCheckedRadioButtonId() == R.id.gr1){
            gr = gr1.getText().toString();
        }else if(grGroup.getCheckedRadioButtonId() == R.id.gr2){
            gr = gr2.getText().toString();
        }else if(grGroup.getCheckedRadioButtonId() == R.id.gr3){
            gr = gr3.getText().toString();
        }else{
            gr = gr4.getText().toString();
        }
        String result = "이름: " + text1.getText().toString();
        result += "\n비밀번호: " + text2.getText().toString();
        result += "\nE-MAIL: " + text3.getText().toString();
        result += "\n전화번호: " + text4.getText().toString();
        result += "\n학과: " + text5.getText().toString();
        result += "\n학번: " + text6.getText().toString();
        result += "\n학년: " + gr;
        result += "\n생일: " + text7.getText().toString();

        Intent Mintent = new Intent(JK1Activity.this, MainActivity.class);
        Mintent.putExtra("result", result);
        setResult(RESULT_OK, Mintent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button6:
                GoToMain(v);
                break;
            case R.id.text1:
                Toast.makeText(getApplicationContext(), "이름을 적는 칸입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.text2:
                Toast.makeText(getApplicationContext(), "비밀번호를 입력하는 칸입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.text3:
                Toast.makeText(getApplicationContext(), "email을 입력하는 칸입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.text4:
                Toast.makeText(getApplicationContext(), "전화번호를 입력하는 칸입니다", Toast.LENGTH_LONG).show();
                break;
            case R.id.text5:
                Toast.makeText(getApplicationContext(), "학과를 입력하는 칸입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.text6:
                Toast.makeText(getApplicationContext(), "학번을 입력하는 칸입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.text7:
                Toast.makeText(getApplicationContext(), "생일을 입력하는 칸입니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.gr1:
                Toast.makeText(getApplicationContext(), "1학년이시군요.", Toast.LENGTH_LONG).show();
                break;
            case R.id.gr2:
                Toast.makeText(getApplicationContext(), "2학년이시군요.", Toast.LENGTH_LONG).show();
                break;
            case R.id.gr3:
                Toast.makeText(getApplicationContext(), "3학년이시군요.", Toast.LENGTH_LONG).show();
                break;
            case R.id.gr4:
                Toast.makeText(getApplicationContext(), "4학년이시군요.", Toast.LENGTH_LONG).show();
                break;
//            case R.id.text8:
//                Toast.makeText(getApplicationContext(), "여덟번째 칸을 눌렀습니다.", Toast.LENGTH_LONG).show();
//                break;
        }
    }
}
