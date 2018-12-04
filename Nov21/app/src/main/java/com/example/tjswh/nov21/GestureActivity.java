package com.example.tjswh.nov21;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class GestureActivity extends AppCompatActivity
        implements GestureOverlayView.OnGesturePerformedListener{

    private GestureLibrary gLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        Toast.makeText(this, "제스쳐를 입력해주세요.", Toast.LENGTH_SHORT).show();

        gLibrary =
                GestureLibraries.fromRawResource(this, R.raw.mygestures);
        if (!gLibrary.load()) {
            finish();
        }

        GestureOverlayView gOverlay =
                (GestureOverlayView) findViewById(R.id.gOverlay);
        gOverlay.addOnGesturePerformedListener(this);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions =
                gLibrary.recognize(gesture);
        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
            String action = predictions.get(0).name;
//            Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
            Intent resultIntent = new Intent(this, MainActivity.class);
            switch (action) {
                case "google":
//                    Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
                    resultIntent.putExtra("result", action);
                    setResult(1, resultIntent);
                    finish();
                    break;
                case "naver":
//                    Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
                    resultIntent.putExtra("result", action);
                    setResult(2, resultIntent);
                    finish();
                    break;
                case "youtube":
//                    Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
                    resultIntent.putExtra("result", action);
                    setResult(3, resultIntent);
                    finish();
                    break;
                case "usw":
//                    Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
                    resultIntent.putExtra("result", action);
                    setResult(4, resultIntent);
                    finish();
                    break;
            }
        }
    }
}
