package com.example.tjswh.intentassgnmt;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class JK2Activity extends AppCompatActivity implements View.OnClickListener{

    private CheckBox btn3;
    private RadioButton btn4;
    private RadioButton btn5;
    private CheckBox btn6;
    private ToggleButton btn7;
    private Switch btn8;
    private FloatingActionButton cart;
    private FloatingActionButton buy;
    private TextView insurText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jk2);

        Button backToM = (Button) findViewById(R.id.button7);
        Button btn1 = (Button) findViewById(R.id.btn1);
        ImageButton btn2 = (ImageButton) findViewById(R.id.btn2);
        btn3 = (CheckBox) findViewById(R.id.btn3);
        btn4 = (RadioButton) findViewById(R.id.btn4);
        btn5 = (RadioButton) findViewById(R.id.btn5);
        btn6 = (CheckBox) findViewById(R.id.btn6);
        btn7 = (ToggleButton) findViewById(R.id.btn7);
        btn8 = (Switch) findViewById(R.id.btn8);
        cart = (FloatingActionButton) findViewById(R.id.cart);
        buy = (FloatingActionButton) findViewById(R.id.buy);
        insurText = (TextView) findViewById(R.id.insurText);

        backToM.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        cart.setOnClickListener(this);
        buy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId())
        {
            case R.id.button7:
                GoToMain(v);
                break;
            case R.id.btn1:
                Toast.makeText(getApplicationContext(), "상세정보를 열었습니다.", Toast.LENGTH_LONG).show();
                btn3.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                btn7.setVisibility(View.VISIBLE);
                btn8.setVisibility(View.VISIBLE);
                insurText.setVisibility(View.VISIBLE);
                break;
            case R.id.btn2:
                Toast.makeText(getApplicationContext(), "이미지 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn3:
                if(btn3.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Color를 White로 변경했습니다.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Color를 Black으로 변경했습니다.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn4:
                Toast.makeText(getApplicationContext(), "256GB로 설정합니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn5:
                Toast.makeText(getApplicationContext(), "512GB로 설정합니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn6:
                Toast.makeText(getApplicationContext(), "용량을 설정합니다.", Toast.LENGTH_LONG).show();
                btn4.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.VISIBLE);
                break;
            case R.id.btn7:
                if(btn7.isChecked()) {
                    Toast.makeText(getApplicationContext(), "보험을 적용합니다", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "보험을 적용하지 않습니다", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn8:
                if(btn8.isChecked()) {
                    if(btn4.isChecked() || btn5.isChecked()) {
                        Toast.makeText(getApplicationContext(), "다음 단계를 진행합니다.", Toast.LENGTH_LONG).show();
                        cart.setVisibility(View.VISIBLE);
                        buy.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(getApplicationContext(), "용량을 선택해주세요.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    cart.setVisibility(View.GONE);
                    buy.setVisibility(View.GONE);
                }
                break;
            case R.id.cart:
                Toast.makeText(getApplicationContext(), "장바구니에 담았습니다.", Toast.LENGTH_LONG).show();
                break;
            case R.id.buy:
                Toast.makeText(getApplicationContext(), "구매 페이지로 이동합니다.", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void GoToMain(View v){
        Intent Mintent = new Intent(JK2Activity.this, MainActivity.class);
        setResult(RESULT_OK, Mintent);
        finish();
    }
}
