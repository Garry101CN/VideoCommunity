package com.bignerdranch.android.videocommunity;

/**
 * Created by LENOVO on 2017/5/21.
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
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

public class DonloadingRecyclerAdapter extends RecyclerView.Adapter<DonloadingRecyclerAdapter.ViewHolder> {
    public static List<ContentlistEntity> contentlistEntities;
    private int sqlTime = 1;
    private Context context;
    private Activity activity;
    private SQLdataSaved sqLdataSaved;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues contentValues;
    private  String enviromentPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getPath();



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
            title = (TextView) itemView.findViewById(R.id.song_list_china_songNme);
            circleImage = (CircleImageViewWan) itemView.findViewById(R.id.song_list_china_image);
            playerButton = (Button) itemView.findViewById(R.id.song_list_c_play);

        }
    }

    public DonloadingRecyclerAdapter(Activity activity) {
        this.activity = FragmentAdapter.activity;
        Log.d(TAG, "RecyclerAdapter: ..............................................");
//        getContentlistEntitiesSQL();
        if (FragmentAdapter.activity == null) {
            Log.d(TAG, "DonloadingRecyclerAdapter: 88888888888888888888888888888888888888888888888888888888888");
        }
        sqLdataSaved = new SQLdataSaved(FragmentAdapter.activity, "downlist.db", null, 1);
        contentValues = new ContentValues();
        db = sqLdataSaved.getWritableDatabase();
        cursor = db.query("downlist", null, null, null, null, null, null);
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
    public void onBindViewHolder(ViewHolder holder, int position){
            String userName = VideoWithController.ContentlistEntitiesInDoloadList.get(position).getName();
            String userAvater = VideoWithController.ContentlistEntitiesInDoloadList.get(position).getProfile_image();
            holder.userName.setText(userName);
        if (VideoWithController.ContentlistEntitiesInDoloadList.get(position).downloaded) {
            holder.title.setText("下载完成");
            String fileName = VideoWithController.ContentlistEntitiesInDoloadList
                    .get(position).getVideo_uri().substring(VideoWithController.ContentlistEntitiesInDoloadList
                            .get(position).getVideo_uri().lastIndexOf("/")) + ".mp4" ;
            contentValues.put("userName", VideoWithController.ContentlistEntitiesInDoloadList.get(position).getName());
            contentValues.put("downloadUri", VideoWithController.ContentlistEntitiesInDoloadList.get(position).getVideo_uri());
            contentValues.put("userAvatar", VideoWithController.ContentlistEntitiesInDoloadList.get(position).getProfile_image());
            contentValues.put("downloadFilePath",fileName);
            db.insert("downlist", null, contentValues);
            contentValues.clear();
            sqlTime ++;
        } else {
            holder.title.setText("下载未完成");
        }
            ImageLoad.getCircleImage(activity, userAvater, holder.circleImage);
    }


    @Override
    public int getItemCount() {
        if (VideoWithController.ContentlistEntitiesInDoloadList.size() > 20) {
            return 20;
        } else if (VideoWithController.ContentlistEntitiesInDoloadList.size() == 0){
            Toast.makeText(FragmentAdapter.activity, "还没有正在下载或者没有下载完的歌曲哦", Toast.LENGTH_SHORT).show();
            return  0;
        } else {
            return VideoWithController.ContentlistEntitiesInDoloadList.size();
        }
    }

    public void getContentlistEntitiesSQL() {
        int SQLIteNum = 0;
//        sqLdataSaved = new SQLdataSaved(activity, "downlist.db", null, 1);
//        db = sqLdataSaved.getWritableDatabase();
//        cursor = db.query("downlist", null, null, null, null, null, null);
//        if (cursor.moveToFirst()) {
//            contentlistEntities = new ArrayList<>();
//            do {
//                ContentlistEntity contentlistEntity = new ContentlistEntity();
//                contentlistEntity.setName(cursor.getString(cursor.getColumnIndex("userName")));
//                contentlistEntity.setVideo_uri(cursor.getString(cursor.getColumnIndex("downloadUri")));
//                contentlistEntity.setText(cursor.getString(cursor.getColumnIndex("title")));
//                contentlistEntity.setProfile_image(cursor.getString(cursor.getColumnIndex("userAvatar")));
//                contentlistEntities.add(SQLIteNum, contentlistEntity);
//                SQLIteNum++;
//            } while (cursor.moveToNext());
//            ifDowned = true;
//        } else {
//            ifDowned = false;
//        }
    }
}


