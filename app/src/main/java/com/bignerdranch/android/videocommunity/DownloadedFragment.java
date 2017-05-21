package com.bignerdranch.android.videocommunity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LENOVO on 2017/5/21.
 */

public class DownloadedFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int pageNum = 2;
    private Activity activity;
    public  Activity getFragmentActivity() {
        return activity;
    }

    public static void setActivity(Activity activity) {
        FragmentAdapter.activity= activity;
    }

    public DownloadedFragment (){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.download_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.download_fragment);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getFragmentActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new DownloadRecyclerAdapter(FragmentAdapter.activity));
        return view;
    }
}

