package com.example.tjswh.dbexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentDB.db";
    public static final String TABLE_STUDENTS = "students";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STUDENTNAME = "studentname";
    public static final String COLUMN_NUMBER = "number";

    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {                   //안드로이드 자바의 onCreate와 다름
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " +        //DB 스키마
                TABLE_STUDENTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_STUDENTNAME
                + " TEXT," + COLUMN_NUMBER + " INTEGER" + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    public void addStudent(Product student) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENTNAME, student.getStudentName());
        values.put(COLUMN_NUMBER, student.getNumber());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }

    public Product findStudentByName(String studentname) {
        String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " +
                COLUMN_STUDENTNAME + " = \"" + studentname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Product student = new Product();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setID(Integer.parseInt(cursor.getString(0)));
            student.setStudentName(cursor.getString(1));
            student.setNumber(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            student = null;
        }

        db.close();
        return student;
    }

    public Product findStudentByNumber(int number) {
        String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " +
                COLUMN_NUMBER + " = \"" + number + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Product student = new Product();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setID(Integer.parseInt(cursor.getString(0)));
            student.setStudentName(cursor.getString(1));
            student.setNumber(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            student = null;
        }

        db.close();
        return student;
    }

    public boolean deleteStudent(String studentname) {
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " +
                COLUMN_STUDENTNAME + " = \"" + studentname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Product student = new Product();

        if (cursor.moveToFirst()) {
            student.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_STUDENTS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(student.getID()) });
            cursor.close();
            result = true;
        }
        db.close();

        return result;
    }

    public boolean deleteAll() {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, null, null);
        return result;
    }
}