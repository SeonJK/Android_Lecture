package com.example.tjswh.nov21_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sub3Activity extends AppCompatActivity {

    private EditText sub3Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);

        Bundle extras = getIntent().getExtras();
        if( extras == null){
            return;
        }
        String question3 = extras.getString("question");
        final TextView getHobbyText = findViewById(R.id.getHobbyText);
        getHobbyText.setText(question3);
        Button subBtn = findViewById(R.id.btn4);
        sub3Text = findViewById(R.id.editText4);

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain(v);
            }
        });
    }

    public void goToMain(View v){
        String answer3 = sub3Text.getText().toString();
        Intent hobbyIntent = new Intent(this, MainActivity.class);
        hobbyIntent.putExtra("answer", answer3);
        setResult(RESULT_OK, hobbyIntent);
        super.finish();
    }
}
