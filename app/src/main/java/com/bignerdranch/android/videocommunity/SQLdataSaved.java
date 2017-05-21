package com.bignerdranch.android.videocommunity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/5/21.
 */

public class SQLdataSaved extends SQLiteOpenHelper {

    private Context context;

    private final String CREAT_SONGLIST_CHINA = "create table downlist (" +
            "numSong integer primary key autoincrement, " +
            "userName text, " +
            "downloadUri text," +
            "userAvatar text," +
            "downloadFilePath text," +
            "title text)";

    private final String CREAT_HISTORY_LIST = "create table HistoryList (" +
            "numSong integer primary key autoincrement, " +
            "userName text, " +
            "downloadUri text," +
            "userAvatar text," +
            "downloadFilePath text," +
            "title text" +
            "currPosition integer)";

    public SQLdataSaved(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_SONGLIST_CHINA);
        db.execSQL(CREAT_HISTORY_LIST);
        Log.d(TAG, "onCreate:       数据库创建成功");
        Toast.makeText(context, "SonggList create successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists downlist");
        db.execSQL("drop table if exists HistoryList");
        onCreate(db);
    }
}

