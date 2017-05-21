package com.bignerdranch.android.videocommunity.DownloadTool;

/**
 * Created by LENOVO on 2017/5/21.
 */

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.bignerdranch.android.videocommunity.DownloadingFragment;
import com.bignerdranch.android.videocommunity.VideoWithController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;
public class DownloadTask extends AsyncTask<String,Integer,Integer> {

    public static final int TYPE_SUCCESS=0;
    public static final int TYPE_FAILD=1;
    public static final int TYPE_PAUSED=2;
    public static final int TYPE_CANCELED=3;
    public static boolean downloadSuccess = false;
    public static int progressDownload = 0;

    private boolean isCanceled=false;
    private boolean isPaused=false;

    private  String fileType;
    private DownloadListener listener;

    private int lastProgress;
    public DownloadTask(DownloadListener listener,String fileType){
        this.listener = listener;
        this.fileType = fileType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... params) {
        HttpURLConnection connection = null;
        InputStream is = null;
        RandomAccessFile saveFile = null;
        File file = null;
        try {
            long downloadedLen = 0;     //The  length of content has already downloaded
            String downloadUrl = params[0];
            URL url = new URL(downloadUrl);

            String enviromentPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    .getPath();
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            if (fileType == null){
                file = new File(enviromentPath + fileName);
            } else {
                file = new File(enviromentPath + fileName + "." + fileType);

            }
            if (file.exists()) {
                downloadedLen = file.length();
            }
            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                return TYPE_FAILD;
            } else if (contentLength == downloadedLen) {
                return TYPE_SUCCESS;
            }
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            connection.setRequestProperty("RANGE", "bytes=" + downloadedLen + "-" + (contentLength-1));

            if (connection.getResponseCode() == HttpURLConnection.HTTP_PARTIAL) {
                Log.d(TAG, "doInBackground: The request is successful.");
                is = connection.getInputStream();
            } else {
                Log.d(TAG, "doInBackground: The request is failed.");
            }
            saveFile = new RandomAccessFile(file, "rw");
            saveFile.seek(downloadedLen);     //Jumping the bytecode which had downloaded
            byte[] b = new byte[1024];
            int total = 0;
            int len;
            while ((len=is.read(b))!=-1) {
                if (isCanceled) {
                    return TYPE_CANCELED;
                } else if (isPaused) {
                    return TYPE_PAUSED;
                } else {
                    total+=len;
                    saveFile.write(b, 0, len);

                    int progress = (int) ((total+downloadedLen) * 100 / contentLength);
                    publishProgress(progress);
                }
            }
            return TYPE_SUCCESS;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (saveFile != null){
                    saveFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILD;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
            progressDownload = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer) {
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            case TYPE_FAILD:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_SUCCESS:
                downloadSuccess = true;
//                DownloadingFragment.adapter.notifyDataSetChanged();
                listener.onSuccess();
                break;
            default:
                break;
        }
    }

    public void pauseDownload(){
        isPaused=true;
    }

    public void canceledDownload(){
        isCanceled=true;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        HttpURLConnection connection = null;
        URL url = new URL(downloadUrl);
        connection=(HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(8000);
        connection.setConnectTimeout(8000);

        if (connection.getInputStream()!=null) {
            return connection.getContentLength();
        }else {
            return 0;
        }
    }
}

