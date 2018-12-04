package com.example.tjswh.dbexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView idView;
    EditText studentBox;
    EditText numberBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idView = (TextView) findViewById(R.id.studentID);
        studentBox = (EditText) findViewById(R.id.studentName);
        numberBox =
                (EditText) findViewById(R.id.studentNumber);
    }

    public void newProduct (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        int number =
                Integer.parseInt(numberBox.getText().toString());
        Product student =
                new Product(studentBox.getText().toString(), number);
        dbHandler.addStudent(student);
        studentBox.setText("");
        numberBox.setText("");
    }

    public void lookupProductName (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Product student =
                dbHandler.findStudentByName(studentBox.getText().toString());
        if (student != null) {
            idView.setText(String.valueOf(student.getID()));
            numberBox.setText(String.valueOf(student.getNumber()));
        } else {
            idView.setText("No Match Found");
        }
    }

    public void lookupProductNumber (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Product student =
                dbHandler.findStudentByNumber(Integer.parseInt(numberBox.getText().toString()));
        if (student != null) {
            idView.setText(String.valueOf(student.getID()));
            studentBox.setText(String.valueOf(student.getStudentName().toString()));
        } else {
            idView.setText("No Match Found");
        }
    }

    public void removeProduct (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,
                null, 1);
        boolean result = dbHandler.deleteStudent(
                studentBox.getText().toString());
        if (result)
        {
            idView.setText("Record Deleted");
            studentBox.setText("");
            numberBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }

    public void removeAll (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,
                null, 1);
        dbHandler.deleteAll();
        idView.setText("All Record Deleted");
        studentBox.setText("");
        numberBox.setText("");

    }
}