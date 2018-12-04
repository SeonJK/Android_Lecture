package com.example.tjswh.dbexam;

public class Product {

    private int _id;
    private String _studentname;
    private int _studentnumber;

    public Product() {
    }

    public Product(int id, String studentname, int number) {
        this._id = id;
        this._studentname = studentname;
        this._studentnumber = number;
    }

    public Product(String studentname, int number) {
        this._studentname = studentname;
        this._studentnumber = number;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setStudentName(String studentName) {
        this._studentname = studentName;
    }

    public String getStudentName() {
        return this._studentname;
    }

    public void setNumber(int number) {
        this._studentnumber = number;
    }

    public int getNumber() {
        return this._studentnumber;
    }
}
