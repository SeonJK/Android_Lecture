package com.example.tjswh.mycam;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton camFab;
    private FloatingActionButton vidFab;
    private FloatingActionButton recFab;
    private LinearLayout linearLayout;
    private Button recBtn;
    private Button playBtn;
    private Button stopBtn;
    private VideoView vv;
    private ImageView iv;

    private static MediaRecorder mediaRecorder;
    private static MediaPlayer mediaPlayer;

    private static final int CAMERA_CAPTURE = 100;
    private static final int VIDEO_CAPTURE = 200;
    private static final int VOICE_RECORD = 300;
    private static final int CREATE_REQUEST_CODE = 40;
    private static final int RECORD_REQUEST_CODE = 41;
    private static final int OPEN_REQUEST_CODE = 42;
    private static final String TAG = "MainActivity";

    private boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton plusFab =  findViewById(R.id.plusFab);
        camFab = (FloatingActionButton) findViewById(R.id.camFab);
        vidFab = (FloatingActionButton) findViewById(R.id.vidFab);
        recFab = (FloatingActionButton) findViewById(R.id.recFab);
        linearLayout = findViewById(R.id.linearLayout);
        recBtn = findViewById(R.id.recBtn);
        playBtn = findViewById(R.id.playBtn);
        stopBtn = findViewById(R.id.stopBtn);
        vv = findViewById(R.id.videoView);
        iv = findViewById(R.id.imageView);


        if(!hasCamera()){
            camFab.setEnabled(false);
            vidFab.setEnabled(false);
            iv.setEnabled(false);
            vv.setEnabled(false);
        }
        if(!hasMicrophone()){
            playBtn.setEnabled(false);
            stopBtn.setEnabled(false);
            recBtn.setEnabled(false);
        }else{
            playBtn.setEnabled(false);
            stopBtn.setEnabled(false);
        }

        requestPermission(Manifest.permission.RECORD_AUDIO,
                VOICE_RECORD);

        camFab.setVisibility(View.INVISIBLE);
        recFab.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
        vidFab.setVisibility(View.INVISIBLE);
        iv.setVisibility(View.INVISIBLE);
        vv.setVisibility(View.INVISIBLE);

        camFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabHide(v);
                iv.setVisibility(View.VISIBLE);
                startCamera(v);
            }
        });
        vidFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabHide(v);
                vv.setVisibility(View.VISIBLE);
                startVideo(v);
            }
        });
        recFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabHide(v);
                linearLayout.setVisibility(View.VISIBLE);
                stopBtn.setEnabled(false);
                playBtn.setEnabled(false);
                recBtn.setEnabled(true);
            }
        });
    }

    private boolean hasCamera() {
        return (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY));
    }

    protected boolean hasMicrophone() {
        PackageManager pmanager = this.getPackageManager();
        return pmanager.hasSystemFeature(
                PackageManager.FEATURE_MICROPHONE);
    }

    public void fabVisible(View v) {
        camFab.setVisibility(View.VISIBLE);
        vidFab.setVisibility(View.VISIBLE);
        recFab.setVisibility(View.VISIBLE);
    }

    public void fabHide(View v){
        camFab.setVisibility(View.INVISIBLE);
        recFab.setVisibility(View.INVISIBLE);
        vidFab.setVisibility(View.INVISIBLE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent resultData){
        Uri currentUri = null;

        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == CREATE_REQUEST_CODE)
            {
                if (resultData != null) {
//                    textView.setText("");
                    Log.d(TAG,  "새파일이 생성됐습니다.");
                }
            } //else if (requestCode == SAVE_REQUEST_CODE) {
//                if (resultData != null) {
//                    currentUri = resultData.getData();
//                    Log.d(TAG, currentUri + "이(가) 저장됐습니다.");
//                    writeFileContent(currentUri);
//                }
//            }
            else if (requestCode == OPEN_REQUEST_CODE) {
                if (resultData != null) {
                    currentUri = resultData.getData();
                    try {
//                        String content =
//                                readFileContent(currentUri);
//                        textView.setText(content);
                        Log.d(TAG, currentUri + "를 불러왔습니다.");

                    } catch (Exception e) {
                        // 에러 처리 코드
//                        Log.e(TAG, "읽는 중에 에러가 났어요~!~!");
                    }
                }
            }
        }
    }
    public void startCamera(View v){
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camIntent, CAMERA_CAPTURE);
    }

    public void startVideo(View v){
        Intent vidIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(vidIntent, VIDEO_CAPTURE);
    }

    public void recordAudio (View v) throws IOException
    {
        isRecording = true;
        stopBtn.setEnabled(true);
        playBtn.setEnabled(false);
        recBtn.setEnabled(false);

        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//            mediaRecorder.setOutputFile(audioFilePath);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
    }

    public void playAudio (View view) throws IOException
    {
        playBtn.setEnabled(false);
        recBtn.setEnabled(false);
        stopBtn.setEnabled(true);

        Intent openIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        openIntent.addCategory(Intent.CATEGORY_OPENABLE);
        openIntent.setType("video/3gp");

        startActivityForResult(openIntent, OPEN_REQUEST_CODE);



        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setDataSource(audioFilePath);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    public void stopAudio (View view)
    {
        stopBtn.setEnabled(false);
        playBtn.setEnabled(true);

        if (isRecording)
        {
            recBtn.setEnabled(false);
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;

            Intent saveIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            saveIntent.addCategory(Intent.CATEGORY_OPENABLE);
            saveIntent.setType("video/3gp");
            saveIntent.putExtra(Intent.EXTRA_TITLE, "myaudio.3gp");

            startActivityForResult(saveIntent, CREATE_REQUEST_CODE);
        } else {
            mediaPlayer.release();


            mediaPlayer = null;
            recBtn.setEnabled(true);
        }
    }


    protected void requestPermission(String permissionType, int requestCode) {
        int permission = ContextCompat.checkSelfPermission(this,
                permissionType);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{permissionType}, requestCode
            );
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RECORD_REQUEST_CODE: {
                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {
                    recBtn.setEnabled(false);
                    Toast.makeText(this,
                            "Record permission required",
                            Toast.LENGTH_LONG).show();
                } else {
                    requestPermission(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            CREATE_REQUEST_CODE);
                }
                return;
            }
            case CREATE_REQUEST_CODE: {
                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {
                    recBtn.setEnabled(false);
                    Toast.makeText(this,
                            "External Storage permission required",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
