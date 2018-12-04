package com.example.tjswh.sep19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToSub1(v);
            }
        });

    }

    public void goToSub1(View v){
        Intent myIntent = new Intent(this, Sub1.class);
        startActivity(myIntent);
    }
}
