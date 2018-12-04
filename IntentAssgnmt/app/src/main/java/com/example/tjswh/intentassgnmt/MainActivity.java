package com.example.tjswh.intentassgnmt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private Button btn5;
    private TextView text1;

    @Override
    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (ImageButton) findViewById(R.id.button2);
        btn2 = (ImageButton) findViewById(R.id.button3);
        btn3 = (ImageButton) findViewById(R.id.button4);
        btn4 = (ImageButton) findViewById(R.id.button5);
        btn5 = (Button) findViewById(R.id.button10);
        text1 = (TextView) findViewById(R.id.text1);
        TextView text2 = (TextView) findViewById(R.id.text2);
        TextView text3 = (TextView) findViewById(R.id.text3);
        TextView text4 = (TextView) findViewById(R.id.text4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToJK1(v);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToJK2(v);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToJK3(v);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToJK4(v);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToJK5(v);
            }
        });

    }

    public void goToJK1(View v){
        Intent JK1Intent = new Intent(MainActivity.this, JK1Activity.class);
        startActivityForResult(JK1Intent, 1);
    }
    public void goToJK2(View v){
        Intent JK2Intent = new Intent(MainActivity.this, JK2Activity.class);
        startActivityForResult(JK2Intent, 2);
    }
    public void goToJK3(View v){
        Intent JK3Intent = new Intent(MainActivity.this, JK3Activity.class);
        startActivityForResult(JK3Intent, 3);
    }
    public void goToJK4(View v){
        Intent JK4Intent = new Intent(MainActivity.this, JK4Activity.class);
        startActivityForResult(JK4Intent, 4);
    }
    public void goToJK5(View v){
        Intent JK5Intent = new Intent(MainActivity.this, JK5Activity.class);
        startActivity(JK5Intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

         switch(requestCode){
             case 1:
                 text1.setVisibility(View.VISIBLE);
                if(resultCode == Activity.RESULT_OK){
                    text1.setText(data.getStringExtra("result"));
                    btn1.setImageResource(R.drawable.done);
                //setReadMore(text1, R.string.defaultText, 2);
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                text1.setText("텍스트 없음");
                }
                break;
             case 2:
                 btn2.setImageResource(R.drawable.done);
                 break;
             case 3:
                 btn3.setImageResource(R.drawable.done);
                 break;
             case 4:
                 btn4.setImageResource(R.drawable.done);
                 break;
        }
    }

    public static void setReadMore(final TextView view, final String text, final int maxLine) {
        final Context context = view.getContext();
        final String expandedText = " ... 더보기";

        if (view.getTag() != null && view.getTag().equals(text)) { //Tag로 전값 의 text를 비교하여똑같으면 실행하지 않음.
            return;
        }
        view.setTag(text); //Tag에 text 저장
        view.setText(text); // setText를 미리 하셔야  getLineCount()를 호출가능
        view.post(new Runnable() { //getLineCount()는 UI 백그라운드에서만 가져올수 있음
            @Override
            public void run() {
                if (view.getLineCount() >= maxLine) { //Line Count가 설정한 MaxLine의 값보다 크다면 처리시작

                    int lineEndIndex = view.getLayout().getLineVisibleEnd(maxLine - 1); //Max Line 까지의 text length

                    String[] split = text.split("\n"); //text를 자름
                    int splitLength = 0;

                    String lessText = "";
                    for (String item : split) {
                        splitLength += item.length() + 1;
                        if (splitLength >= lineEndIndex) { //마지막 줄일때!
                            if (item.length() >= expandedText.length()) {
                                lessText += item.substring(0, item.length() - (expandedText.length())) + expandedText;
                            } else {
                                lessText += item + expandedText;
                            }
                            break; //종료
                        }
                        lessText += item + "\n";
                    }
                    SpannableString spannableString = new SpannableString(lessText);
                    spannableString.setSpan(new ClickableSpan() {//클릭이벤트
                        @Override
                        public void onClick(View v) {
                            view.setText(text);
                        }

                        @Override
                        public void updateDrawState(TextPaint ds) { //컬러 처리
                            ds.setColor(ContextCompat.getColor(context, R.color.cardview_dark_background));
                        }
                    }, spannableString.length() - expandedText.length(), spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    view.setText(spannableString);
                    view.setMovementMethod(LinkMovementMethod.getInstance());
                }
            }
        });
    }
}
