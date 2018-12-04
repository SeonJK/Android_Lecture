package com.example.tjswh.intentassgnmt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class JK5Activity extends AppCompatActivity {
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
//    private ListView myListView;
    ViewPager vp;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jk5);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);
        vp = (ViewPager) findViewById(R.id.vp);
        setSupportActionBar(toolbar);
        pagerAdapter tabPagerAdapter = new pagerAdapter(getSupportFragmentManager());
        vp.setAdapter(tabPagerAdapter);
        vp.setCurrentItem(0);
        tabLayout.setupWithViewPager(vp);


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems);
        ListFragment menuListFrgmt = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment4);
        menuListFrgmt.setListAdapter(adapter) ;
//        myListView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addListItem();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    View.OnClickListener undoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listItems.remove(listItems.size() - 1);
            adapter.notifyDataSetChanged();
            Snackbar.make(view, "Item removed", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void addListItem() {
        SimpleDateFormat dateformat =
                new SimpleDateFormat("HH:mm:ss MM/dd/yyyy",
                        Locale.US);
        listItems.add(dateformat.format(new Date()));
        adapter.notifyDataSetChanged();
    }

    static int PAGE_NUMBER = 4;

    private class pagerAdapter extends FragmentPagerAdapter {

        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Toast.makeText(getApplicationContext(), "첫번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    return new FirstFragment();
                case 1:
                    Toast.makeText(getApplicationContext(), "두번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    return new SecondFragment();
                case 2:
                    Toast.makeText(getApplicationContext(), "세번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    return new ThirdFragment();
                case 3:
                    Toast.makeText(getApplicationContext(), "네번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    return new ListFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return PAGE_NUMBER;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
//                    Toast.makeText(getApplicationContext(), "첫번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    return getResources().getString(R.string.frag1);
                case 1:
//                    Toast.makeText(getApplicationContext(), "두번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    return getResources().getString(R.string.frag2);
                case 2:
//                    Toast.makeText(getApplicationContext(), "세번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    return getResources().getString(R.string.frag3);
                case 3:
//                    Toast.makeText(getApplicationContext(), "세번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
                    return getResources().getString(R.string.frag4);
                default:
                    return null;
            }


        }
    }
//    private class pagerAdapter extends FragmentStatePagerAdapter {
//        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public android.support.v4.app.Fragment getItem(int position) {
//            switch (position) {
//                case 0:
//                    Toast.makeText(getApplicationContext(), "첫번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
//                    return new FirstFragment();
//                case 1:
//                    Toast.makeText(getApplicationContext(), "두번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
//                    return new SecondFragment();
//                case 2:
//                    Toast.makeText(getApplicationContext(), "세번째 fragment 버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
//                    return new ThirdFragment();
//                default:
//                    return null;
//            }
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//    }
}
