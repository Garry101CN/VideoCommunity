package com.bignerdranch.android.videocommunity;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by LENOVO on 2017/5/19.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    public MyViewHolder(View v) {
        super(v);
    }

    public void onBindImageViewInHolder(Activity activity
            , ContentlistEntity contentlistEntity
    , VideoPlayer player) {}
}
