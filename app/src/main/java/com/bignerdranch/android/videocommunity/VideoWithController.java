package com.bignerdranch.android.videocommunity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;

import com.bignerdranch.android.videocommunity.DownloadTool.DownloadService;
import com.bignerdranch.android.videocommunity.DownloadTool.FileDownloader;

import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.List;


public class VideoWithController extends AppCompatActivity implements View.OnClickListener{
    private  VideoPlayerWithController playerWithController;
    private SurfaceView surfaceView;
    private Toolbar toolbar;
    private Button downloadButton;
    private String url;
    private String userName;
    private String userAvatar;
    private String title;
    private FileDownloader fileDownloader;
    public static List<ContentlistEntity> ContentlistEntitiesInDoloadList = new ArrayList<>();
    private SQLdataSaved sqLdataSaved;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues contentValues;

    private final String TAG = "VideoWithController";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_with_controller);
        surfaceView = (SurfaceView) findViewById(R.id.controller_surface);
        downloadButton = (Button) findViewById(R.id.controller_download);
        toolbar = (Toolbar) findViewById(R.id.controller_toolBar);

        toolbar.setTitle("百思不得姐");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        downloadButton.setOnClickListener(this);

        sqLdataSaved = new SQLdataSaved(this, "downlist.db", null, 1);
        contentValues = new ContentValues();
        db = sqLdataSaved.getWritableDatabase();
        cursor = db.query("downlist", null, null, null, null, null, null);

        fileDownloader = new FileDownloader(url);
        FileDownloader.setActivity(this);
        Intent intentService = new Intent(this,DownloadService.class);
        startService(intentService);     //     启动服务
        bindService(intentService,fileDownloader.getConnection(),BIND_AUTO_CREATE);     //     绑定服务

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        userName = intent.getStringExtra("userName");
        userAvatar = intent.getStringExtra("userAvatar");
        title = intent.getStringExtra("title");

        playerWithController = new VideoPlayerWithController(this, surfaceView, url);
        playerWithController.playPrepare();
    }

    @Override
    public void onClick(View v) {
        if (fileDownloader == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.controller_download:
                ContentlistEntity content = new ContentlistEntity();
                content.setText(title);
                content.setName(userName);
                content.setVideo_uri(url);
                content.setProfile_image(userAvatar);
                ContentlistEntitiesInDoloadList.add(content);
                int i = ContentlistEntitiesInDoloadList.size();
                Log.d("main", "onClick: " + url);
                Toast.makeText(this, "aaaaaaaa", Toast.LENGTH_SHORT).show();
                fileDownloader.beginDownLoad(url,
                        "mp4");

//                DownloadingFragment.recyclerView.notifyAll();
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
               int i =  playerWithController.getCurrentPosition();
                Log.d(TAG, "onClick: finishfinishfinishfinishfinish");
                contentValues.put("userName", userName);
                contentValues.put("downloadUri",url);
                contentValues.put("userAvatar", userAvatar);
                contentValues.put("currPosition",i);
                db.insert("downlist", null, contentValues);
                contentValues.clear();
                playerWithController.relesae();
                finish();
                break;
        }
        return true;
    }
}
