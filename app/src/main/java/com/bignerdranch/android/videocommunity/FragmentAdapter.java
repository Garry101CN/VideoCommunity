package com.bignerdranch.android.videocommunity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by LENOVO on 2017/5/21.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    public final  int COUNT = 2;
    private String[] titles = new String[] {"已下载", "正在下载"};
    private Context context;
    public static Activity activity;
    private DownloadedFragment downloadedFragment;


    public FragmentAdapter(FragmentManager fm, Context context, Activity activity) {
        super(fm);
        this.context = context;
        this.activity = activity;
        DownloadedFragment.setActivity(activity);
    }

    @Override
    public Fragment getItem(int position) {
        downloadedFragment = new DownloadedFragment();
        if (position == 1) {
            return new DownloadingFragment();
        } else
            return downloadedFragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

