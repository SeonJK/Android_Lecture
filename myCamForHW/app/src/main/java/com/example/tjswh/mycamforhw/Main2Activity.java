package com.example.tjswh.mycamforhw;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    private static final int VIDEO_CAPTURE = 100;
    private static final int VIDEO_PLAY = 200;
//    private static final int VOICE_RECORD = 300;
    private static final int CREATE_REQUEST_CODE = 40;
    private static final int RECORD_REQUEST_CODE = 41;
    private static final int OPEN_REQUEST_CODE = 42;
    private static final String TAG = "MainActivity";

    private FloatingActionButton plusFab;
    private FloatingActionButton camFab;
    private FloatingActionButton vidFab;
    private FloatingActionButton recFab;
    private Button selectBtn;
    private VideoView vv;
    private TextView tt;

    private Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        plusFab = findViewById(R.id.plusFab2);
        camFab = findViewById(R.id.camFab2);
        vidFab = findViewById(R.id.vidFab2);
        recFab = findViewById(R.id.recFab2);
        selectBtn = findViewById(R.id.selectBtn);
        vv = findViewById(R.id.videoView);
        tt = findViewById(R.id.tt);

        if(!hasCamera()){
            camFab.setEnabled(false);
            vidFab.setEnabled(false);
            selectBtn.setVisibility(View.GONE);
            vv.setVisibility(View.GONE);
            tt.setVisibility(View.VISIBLE);
        }

        tt.setVisibility(View.GONE);
        camFab.setVisibility(View.GONE);
        recFab.setVisibility(View.GONE);
        vidFab.setVisibility(View.GONE);

        plusFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camFab.getVisibility() == View.GONE){
                    fabVisible(v);
                }else {
                    fabHide(v);
                }
            }
        });
        camFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabHide(v);
                startCamera(v);
            }
        });
        vidFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabHide(v);
            }
        });
        recFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMain(v);
            }
        });
        selectBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("video/*");

                startActivityForResult(intent, OPEN_REQUEST_CODE);
            }
        });

        //        try {
//            Intent intent = getIntent();
//            Bundle bundle = intent.getExtras();
//
//            String getPath = bundle.getString("savedPath");
//            vv.setVideoPath(getPath);
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }


    }

    private void goMain(View v) {
        Intent m1 = new Intent(this, MainActivity.class);
        startActivity(m1);
    }

    private boolean hasCamera() {
        return (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY));
    }

    public void fabVisible(View v) {
        camFab.setVisibility(View.VISIBLE);
        vidFab.setVisibility(View.VISIBLE);
        recFab.setVisibility(View.VISIBLE);
    }

    public void fabHide(View v){
        camFab.setVisibility(View.GONE);
        recFab.setVisibility(View.GONE);
        vidFab.setVisibility(View.GONE);
    }

    public void startCamera(View v){
        Intent camIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(camIntent, VIDEO_CAPTURE);
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        videoUri = null;

        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
//                Toast.makeText(this, "Video saved to:\n" +
//                        currentUri, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("video/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_TITLE, "newvideo.mp4");

                startActivity(intent);

                videoUri = data.getData();
                vv.setVideoURI(videoUri);
                MediaController mediaController = new           //한번 터치했을 때 컨트롤 할 수 있는 화면을 나타내게 하는것
                        MediaController(this);
                mediaController.setAnchorView(vv);
                vv.setMediaController(mediaController);
                vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Log.i("VideoPlayer", "Duration = " +
                                vv.getDuration());
                    }
                });

                vv.start();
//                startActivityForResult(intent, CREATE_REQUEST_CODE);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video.",
                        Toast.LENGTH_LONG).show();
            }
        }
        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == CREATE_REQUEST_CODE) {
//                if (data != null) {
////                    textView.setText("");
//                    Log.d(TAG, "새파일이 생성됐습니다.");
//                }
//            }
            if(requestCode == OPEN_REQUEST_CODE){
                if (data != null) {
                    videoUri = data.getData();
                    try {
//                        String content =
//                                readFileContent(currentUri);
//                        textView.setText(content);
                        vv.setVideoURI(videoUri);

                        MediaController mediaController = new           //한번 터치했을 때 컨트롤 할 수 있는 화면을 나타내게 하는것
                                MediaController(this);
                        mediaController.setAnchorView(vv);
                        vv.setMediaController(mediaController);
                        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                Log.i("VideoPlayer", "Duration = " +
                                        vv.getDuration());
                            }
                        });

                        vv.start();
                        Log.d(TAG, videoUri + "를 불러왔습니다.");
                    } catch (Exception e) {
                        // 에러 처리 코드
                        Log.e(TAG, "읽는 중에 에러가 났어요~!~!");
                    }
                }
            }
        }
    }
}
