package com.bignerdranch.android.videocommunity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.imageloadingwan.CircleImageViewWan;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/5/21.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<ContentlistEntity> contentlistEntities;
    private Context context;
    private Activity activity;
    private SQLdataSaved sqLdataSaved;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues contentValues;
    private boolean ifDowned = false;


    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView userName;
        TextView title;
        CircleImageViewWan circleImage;
        Button playerButton;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            userName = (TextView) itemView.findViewById(R.id.song_list_c_singer);
            circleImage = (CircleImageViewWan) itemView.findViewById(R.id.song_list_china_image);
            playerButton = (Button) itemView.findViewById(R.id.song_list_c_play);
            title = (TextView) itemView.findViewById(R.id.song_list_china_songNme);
        }
    }

    public HistoryAdapter(Activity activity) {
        this.activity = activity;
        Log.d(TAG, "RecyclerAdapter: ..............................................");
        getContentlistEntitiesSQL();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.downloaded_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
            String userName = contentlistEntities.get(position).getName();
            String title = contentlistEntities.get(position).getText();
            String userAvater = contentlistEntities.get(position).getProfile_image();
            holder.userName.setText(userName);
            holder.title.setText(contentlistEntities.get(position).getVideotime());
            ImageLoad.getCircleImage(activity, userAvater, holder.circleImage);
        holder.playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentAdapter.activity, VideoWithController.class);
                intent.putExtra("url", contentlistEntities.get(position).getVideo_uri());
                FragmentAdapter.activity.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (ifDowned) {
            return contentlistEntities.size();
        } else {
            Toast.makeText(FragmentAdapter.activity, "还没有播放过视频哦", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public void getContentlistEntitiesSQL() {
        int SQLIteNum = 0;
        sqLdataSaved = new SQLdataSaved(activity, "downlist.db", null, 1);
        db = sqLdataSaved.getWritableDatabase();
        cursor = db.query("HistoryList", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            contentlistEntities = new ArrayList<>();
            do {
                ContentlistEntity contentlistEntity = new ContentlistEntity();
                contentlistEntity.setName(cursor.getString(cursor.getColumnIndex("userName")));
                contentlistEntity.setVideo_uri(cursor.getString(cursor.getColumnIndex("downloadUri")));
                contentlistEntity.setText(cursor.getString(cursor.getColumnIndex("title")));
                contentlistEntity.setProfile_image(cursor.getString(cursor.getColumnIndex("userAvatar")));
                contentlistEntity.setVideotime(cursor.getString(cursor.getColumnIndex("currPosition")).toString());
                contentlistEntities.add(SQLIteNum, contentlistEntity);
                SQLIteNum++;
            } while (cursor.moveToNext());
            ifDowned = true;
        } else {
            ifDowned = false;
        }
    }
}

