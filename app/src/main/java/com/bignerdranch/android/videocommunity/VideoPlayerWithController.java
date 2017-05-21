package com.bignerdranch.android.videocommunity;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/5/17.
 */

public class VideoPlayerWithController implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener,
        MediaController.MediaPlayerControl {
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private SQLdataSaved sqLdataSaved;
    private Cursor cursor;
    private MediaPlayer mediaPlayer;
    private Activity activity;
    private MediaController mediaController;
    private Handler handler = new Handler();
    private String url;
    private  String enviromentPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getPath();

    public VideoPlayerWithController(Activity activity,SurfaceView surfaceView, String url) {
        this.activity = activity;
        this.surfaceView = surfaceView;
        this.url = url;
    }

    public void playPrepare() {
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mediaController != null){
                    mediaController.show();
                }
                return false;
            }
        });

    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {

        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return mediaPlayer.getAudioSessionId();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();

        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(surfaceView);
        handler.post(new Runnable() {

            public void run() {
                mediaController.setEnabled(true);
                mediaController.show();
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        String fileName =url.substring(url.lastIndexOf("/")) + ".mp4" ;
        File file = new File(fileName);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDisplay(surfaceHolder);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(this);
        try {
            if (file.exists()) {
                mediaPlayer.setDataSource(file.getPath());
            } else {
                mediaPlayer.setDataSource(activity, Uri.parse(url));
            }
            mediaPlayer.prepare();
            mediaController = new MediaController(activity);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void relesae() {
        mediaPlayer.release();
    }
}
