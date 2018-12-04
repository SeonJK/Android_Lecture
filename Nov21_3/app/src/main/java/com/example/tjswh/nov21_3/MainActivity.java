package com.example.tjswh.nov21_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings webSettings;
    Button google;
    Button naver;
    Button youtube;
    Button usw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        google = findViewById(R.id.google);
        naver = findViewById(R.id.naver);
        youtube = findViewById(R.id.youtube);
        usw = findViewById(R.id.usw);
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

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
}
