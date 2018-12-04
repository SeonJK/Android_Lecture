package com.example.tjswh.intentassgnmt;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

//    private static String TAG = "recyclerview_example";
//
//    private ArrayList<item> myArrayList;
//    private CustomAdapter myAdapter;
//    private RecyclerView myRecyclerView;
//    private LinearLayoutManager mLinearLayoutManager;
//    private int count = -1;

    public FirstFragment()
    {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_first);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_first, null);
        return layout;
    }
}
