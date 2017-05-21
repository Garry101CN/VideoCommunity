package com.bignerdranch.android.videocommunity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.bignerdranch.android.videocommunity.DownloadTool.FileDownloader;


public class DownLoadPlay extends AppCompatActivity {
    private ViewPager viewPager;
    private  TabLayout tableLayout;
    private FragmentAdapter adapter;
    private Toolbar toolbar;
    private static final String TAG = "DownLoadPlay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_play);

        viewPager = (ViewPager) findViewById(R.id.download_viewpager);
        adapter = new FragmentAdapter(getSupportFragmentManager(), this, this);
        toolbar = (Toolbar) findViewById(R.id.download_toolbar) ;
        viewPager.setAdapter(adapter);
        tableLayout = (TabLayout) findViewById(R.id.download_tab);
        tableLayout.setupWithViewPager(viewPager);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        int lastNum = -1;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
