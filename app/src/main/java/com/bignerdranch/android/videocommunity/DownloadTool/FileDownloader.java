package com.bignerdranch.android.videocommunity.DownloadTool;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.bignerdranch.android.filedownloade.*;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/5/21.
 */
public class FileDownloader {

    public static Activity activity;

    private String downloadUrl;

    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public ServiceConnection getConnection() {
        return connection;
    }

    public FileDownloader(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public FileDownloader( ) {}

    public void beginDownLoad(String url,String fileType) {
        if (fileType != null) {
            Log.d("FileLoader 进入类", "beginDownLoad: FileType!= null"+fileType);
            downloadBinder.startDownload(url, fileType);
        } else {
            Log.d("FileLoader 进入类", "beginDownLoad: FileType += null"+fileType);
            downloadBinder.startDownload(url);
        }

    }

    public void pauseDownload() {
        downloadBinder.pauseDownload();
    }

    public void cancleDownload() {
        downloadBinder.canceleDownload();
    }

    public  static void setActivity(Activity activityMethod) {
        activity = activityMethod;
    }

    public static int getFileDownloadprogress() {
        Log.d(TAG, "getFileDownloadprogress: THE VALUE OF FILE DOWNLOADING NOW IS " + DownloadTask.progressDownload);
        return com.bignerdranch.android.filedownloade.DownloadTask.progressDownload;
    }

}

