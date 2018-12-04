package com.example.tjswh.nov21_2;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView nameRslt;
    TextView ageRslt;
    TextView hobbyRslt;
    TextView nameText;
    TextView ageText;
    TextView hobbyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nameBtn = findViewById(R.id.nameBtn);
        Button ageBtn = findViewById(R.id.ageBtn);
        Button hobbyBtn = findViewById(R.id.hobbyBtn);
        nameRslt = findViewById(R.id.nameRslt);
        ageRslt = findViewById(R.id.ageRslt);
        hobbyRslt = findViewById(R.id.hobbyRslt);
        nameText = findViewById(R.id.nameText);
        ageText = findViewById(R.id.ageText);
        hobbyText = findViewById(R.id.hobbyText);

        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSub(v);
            }
        });
        ageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goToSub2(v);
            }
        });
        hobbyBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goToSub3(v);
            }
        });
    }

    public void goToSub(View v){
        Intent subIntent = new Intent(this, SubActivity.class);
        String nameString = nameText.getText().toString();
        subIntent.putExtra("question", nameString);
        startActivityForResult(subIntent, 1);
    }

    public void goToSub2(View v){
        Intent sub2Intent = new Intent(this, Sub2Activity.class);
        String ageString = ageText.getText().toString();
        sub2Intent.putExtra("question", ageString);
        startActivityForResult(sub2Intent, 2);
    }

    public void goToSub3(View v){
        Intent sub3Intent = new Intent(this, Sub3Activity.class);
        String hobbyString = hobbyText.getText().toString();
        sub3Intent.putExtra("question", hobbyString);
        startActivityForResult(sub3Intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                if(resultCode == Activity.RESULT_OK) {
                    nameRslt.setText(data.getStringExtra("answer"));
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    nameRslt.setText("텍스트 없음");
                }
                break;
            case 2:
                if(resultCode == Activity.RESULT_OK) {
                    ageRslt.setText(data.getStringExtra("answer"));
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    ageRslt.setText("텍스트 없음");
                }
                break;
            case 3:
                if(resultCode == Activity.RESULT_OK) {
                    hobbyRslt.setText(data.getStringExtra("answer"));
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    hobbyRslt.setText("텍스트 없음");
                }
                break;
        }
    }
}
