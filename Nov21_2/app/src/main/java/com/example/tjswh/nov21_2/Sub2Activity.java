package com.example.tjswh.nov21_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sub2Activity extends AppCompatActivity {

    private EditText sub2Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        Bundle extras = getIntent().getExtras();
        if( extras == null){
            return;
        }
        String question2 = extras.getString("question");
        final TextView getAgeText = findViewById(R.id.getAgeText);
        getAgeText.setText(question2);
        Button subBtn = findViewById(R.id.btn4);
        sub2Text = findViewById(R.id.editText4);

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain(v);
            }
        });
    }

    public void goToMain(View v){
        String answer2 = sub2Text.getText().toString();
        Intent ageIntent = new Intent(this, MainActivity.class);
        ageIntent.putExtra("answer", answer2);
        setResult(RESULT_OK, ageIntent);
        super.finish();
    }
}
