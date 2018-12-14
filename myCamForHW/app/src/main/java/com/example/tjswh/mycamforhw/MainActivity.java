package com.example.tjswh.mycamforhw;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton plusFab;
    private FloatingActionButton camFab;
    private FloatingActionButton vidFab;
    private FloatingActionButton recFab;

    private static Button recBtn;
    private static Button playBtn;
    private static Button stopBtn;

    private static String audioFilePath;
    private static MediaRecorder mediaRecorder;
    private static MediaPlayer mediaPlayer;

    private static final int VIDEO_CAPTURE = 100;
//    private static final int VIDEO_PLAY = 200;
    private static final int VOICE_RECORD = 300;
    private static final int CREATE_REQUEST_CODE = 40;
    private static final int RECORD_REQUEST_CODE = 41;
    private static final int STORAGE_REQUEST_CODE = 42;
    private static final int OPEN_REQUEST_CODE = 43;
    private static final String TAG = "MainActivity";

    private boolean isRecording = false;
    private Uri currentUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        plusFab =  findViewById(R.id.plusFab);
        camFab = (FloatingActionButton) findViewById(R.id.camFab);
        vidFab = (FloatingActionButton) findViewById(R.id.vidFab);
        recFab = (FloatingActionButton) findViewById(R.id.recFab);
        recBtn = findViewById(R.id.recBtn);
        playBtn = findViewById(R.id.playBtn);
        stopBtn = findViewById(R.id.stopBtn);
//        vv = findViewById(R.id.videoView);
//        iv = findViewById(R.id.imageView);


        if(!hasCamera()){
            camFab.setEnabled(false);
            vidFab.setEnabled(false);
        }
        if(!hasMicrophone()){
            playBtn.setEnabled(false);
            stopBtn.setEnabled(false);
            recBtn.setEnabled(false);
        }else{
            playBtn.setEnabled(false);
            stopBtn.setEnabled(false);
        }

        audioFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/myaudio.3gp";

        requestPermission(Manifest.permission.RECORD_AUDIO,
                VOICE_RECORD);

        camFab.setVisibility(View.GONE);
        recFab.setVisibility(View.GONE);
        vidFab.setVisibility(View.GONE);

        plusFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camFab.getVisibility() == View.GONE){
                    fabVisible(v);
                }else if(camFab.getVisibility() == View.VISIBLE){
                    fabHide(v);
                }
            }
        });
        camFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabHide(v);
//                iv.setVisibility(View.VISIBLE);
                startCamera(v);
            }
        });
        vidFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMain2(v);
            }
        });
        recFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabHide(v);
            }
        });
        recBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    recordAudio(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        camFab.setVisibility(View.GONE);
        recFab.setVisibility(View.GONE);
        vidFab.setVisibility(View.GONE);
    }

    private void goMain2(View v) {
        Intent m2 = new Intent(this, Main2Activity.class);

//        Bundle bundle = new Bundle();
//        bundle.putString("savedPath", currentUri.toString());
//        if(!currentUri.toString().equals("null")){
//            m2.putExtras(bundle);
//        }

        startActivity(m2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        currentUri = null;

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CREATE_REQUEST_CODE) {
                if (resultData != null) {
//                    textView.setText("");
                    Log.d(TAG, "새파일이 생성됐습니다.");
                }
            }
            else if (requestCode == OPEN_REQUEST_CODE) {
                if (resultData != null) {
                    currentUri = resultData.getData();
                    try {
//                        String content =
////                                readFileContent(currentUri);
////                        textView.setText(content);
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(currentUri.toString());
                        Log.d(TAG, currentUri + "를 불러왔습니다.");

                    } catch (Exception e) {
                        // 에러 처리 코드
                        Log.e(TAG, "읽는 중에 에러가 났어요~!~!");
                    }
                }
            }
        }

        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                currentUri = resultData.getData();
//                Toast.makeText(this, "Video saved to:\n" +
//                        currentUri, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("video/x-msvideo");
                intent.putExtra(Intent.EXTRA_TITLE, "newvideo.avi");

                startActivityForResult(intent, CREATE_REQUEST_CODE);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "Video recording cancelled.",
                            Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    public void startCamera(View v){
        Intent camIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(camIntent, VIDEO_CAPTURE);
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
            mediaRecorder.setOutputFile(audioFilePath);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
    }

    public void playAudio (View view) throws IOException
    {
        playBtn.setEnabled(false);
        recBtn.setEnabled(false);
        stopBtn.setEnabled(true);

//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setDataSource(audioFilePath);

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");

        startActivityForResult(intent, OPEN_REQUEST_CODE);

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
                            STORAGE_REQUEST_CODE);
                }
                return;
            }
            case STORAGE_REQUEST_CODE: {
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
