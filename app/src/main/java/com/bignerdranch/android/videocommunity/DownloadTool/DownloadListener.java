package com.bignerdranch.android.videocommunity.DownloadTool;

/**
 * Created by LENOVO on 2017/5/21.
 */


public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}