package com.example.tjswh.nov21_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private EditText sub1Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Bundle extras = getIntent().getExtras();
        if( extras == null){
            return;
        }
        String question = extras.getString("question");
        final TextView getNameText = findViewById(R.id.getNameText);
        getNameText.setText(question);
        Button subBtn = findViewById(R.id.btn4);
        sub1Text = findViewById(R.id.editText2);

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain(v);
            }
        });
    }

    public void goToMain(View v){
        String answer = sub1Text.getText().toString();
        Intent nameIntent = new Intent(this, MainActivity.class);
        nameIntent.putExtra("answer", answer);
        setResult(RESULT_OK, nameIntent);
        super.finish();
    }
}
