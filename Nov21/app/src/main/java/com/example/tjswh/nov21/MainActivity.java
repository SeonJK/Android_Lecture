package com.example.tjswh.nov21;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnDoubleTapListener,
                    GestureDetector.OnGestureListener{

    private EditText srcText;
    private ImageButton btn;
    private WebView webView;
    private WebSettings webSettings;
    private GestureDetector gDetector;
    String url;
    Button google;
    Button naver;
    Button youtube;
    Button usw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        google = findViewById(R.id.google);
        naver = findViewById(R.id.naver);
        youtube = findViewById(R.id.youtube);
        usw = findViewById(R.id.usw);
        srcText = findViewById(R.id.srcText);
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        this.gDetector = new GestureDetector(this, this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebPage(v);
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebPage(v);
            }
        });
        naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebPage(v);
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebPage(v);
            }
        });
        usw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebPage(v);
            }
        });
    }

    public void showWebPage(View v) {
        switch (v.getId()) {
            case R.id.btn:
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
    public boolean onTouchEvent(MotionEvent event) {
        this.gDetector.onTouchEvent(event);
        // 오버라이딩한 슈퍼 클래스의 메서드를 호출한다
        return super.onTouchEvent(event);
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
        webView.goBack();
        return false;
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
}
