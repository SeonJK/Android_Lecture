package com.example.tjswh.nov21;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private EditText srcText;
    private ImageButton srcBtn;
    private WebView webView;
    private WebSettings webSettings;
    private ImageButton gtrBtn;
    String url;
    private Button google;
    private Button naver;
    private Button youtube;
    private Button usw;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srcBtn = findViewById(R.id.srcBtn);
        gtrBtn = findViewById(R.id.gtrBtn);
        google = findViewById(R.id.google);
        naver = findViewById(R.id.naver);
        youtube = findViewById(R.id.youtube);
        usw = findViewById(R.id.usw);
        srcText = findViewById(R.id.srcText);
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        srcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebPage(v);
            }
        });
        gtrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goGestureActivity(v);
            }
        });

//        google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showWebPage(v);
//            }
//        });
//        naver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showWebPage(v);
//            }
//        });
//        youtube.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showWebPage(v);
//            }
//        });
//        usw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showWebPage(v);
//            }
//        });

        google.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent e){
                webView.loadUrl("http://www.google.com");
                return false;
            }
        });
        naver.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent e){
                webView.loadUrl("http://www.naver.com");
                return false;
            }
        });
        youtube.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent e){
//                webView.loadUrl("http://www.youtube.com");
                showWebPage(v);
                return false;
            }
        });
        usw.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent e){
//                webView.loadUrl("http://www.suwon.ac.kr");
                showWebPage(v);
                return false;
            }
        });
    }

    private void goGestureActivity(View v) {
        Intent gIntent = new Intent(this, GestureActivity.class);

        startActivityForResult(gIntent, 30);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){
            case 1:
                webView.loadUrl("http://www.google.com");
                break;
            case 2:
                webView.loadUrl("http://www.naver.com");
                break;
            case 3:
                webView.loadUrl("http://www.youtube.com");
                break;
            case 4:
                webView.loadUrl("http://m.suwon.ac.kr");
                break;
        }
    }


    public void showWebPage(View v) {
        switch (v.getId()) {
            case R.id.srcBtn:
                url = srcText.getText().toString();
                if (url.startsWith("http://")) {
                    webView.loadUrl(url);
                } else {
                    webView.loadUrl("http://" + url);
                }
                break;

            case R.id.google:
                webView.loadUrl("http://www.google.com");
                break;
            case R.id.naver:
                webView.loadUrl("http://www.naver.com");
                break;
            case R.id.youtube:
                webView.loadUrl("http://www.youtube.com");
                break;
            case R.id.usw:
                webView.loadUrl("http://m.suwon.ac.kr");
                break;
        }
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
