package com.bignerdranch.android.videocommunity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/5/17.
 */

public class Row {
    public class VideoPlayer  implements MediaPlayer.OnCompletionListener
            , MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback {

        private Activity activity;
        private SurfaceView surfaceView;
        private SurfaceHolder holder;
        private ProgressBar progressBar;
        private Display display;
        private MediaPlayer mediaPlayer;

        public  VideoPlayer(Activity activity, SurfaceView surfaceView, ProgressBar progressBar) {
            this.activity = activity;
            this.surfaceView = surfaceView;
            this.progressBar = progressBar;
        }

        public void playVideo(String url) {
            holder = surfaceView.getHolder();
            holder.addCallback(this);
            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setOnCompletionListener(this);

            try {
                mediaPlayer.setDataSource(String.valueOf(Uri.parse(url)));

            } catch (Exception e) {
                e.printStackTrace();
            }
            display = activity.getWindowManager().getDefaultDisplay();
        }

        public void pauseVideo() {
            mediaPlayer.pause();

        }

        @Override
        public void onCompletion(MediaPlayer mp) {

        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.setVolume(0.0f, 0.0f);
            mediaPlayer.start();

            while(true) {
                if (mediaPlayer.isPlaying()) {
                    progressBar.setMax(mediaPlayer.getDuration());
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (true) {
                                    int currentPosition = mediaPlayer.getCurrentPosition();
                                    progressBar.setProgress(currentPosition);
                                    sleep(400);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    break;
                }
            }
        }

        @Override
        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            //when SurfaceView is created,this method will be run
            mediaPlayer.setDisplay(holder);
            mediaPlayer.prepareAsync();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

    public class VideoPlayer2  implements MediaPlayer.OnCompletionListener
            , MediaPlayer.OnPreparedListener,MediaPlayer.OnInfoListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback {

        private Activity activity;
        private SurfaceView surfaceView;
        private SurfaceHolder holder;
        private ProgressBar progressBar;
        private Display display;
        private MediaPlayer mediaPlayer;

        public  VideoPlayer2(Activity activity, SurfaceView surfaceView, ProgressBar progressBar) {
            this.activity = activity;
            this.surfaceView = surfaceView;
            this.progressBar = progressBar;
        }

        public void continuePlay() {
            mediaPlayer.start();
        }

        public int getCurrentPosition() {
            return mediaPlayer.getCurrentPosition();
        }
        public void playVideo(String url) {
            Log.d(TAG, "playVideo: beginbeginbeginbeginbeginbeginbeginbeginbeginbegin" + url);
            holder = surfaceView.getHolder();
            holder.addCallback(this);
            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(this);

            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setOnCompletionListener(this);


            try {
                mediaPlayer.setDataSource(String.valueOf(Uri.parse(url)));
                Log.d(TAG, "playVideo: setDataSourcesetDataSourcesetDataSourcesetDataSourcesetDataSourcesetDataSourcesetDataSourcesetDataSource");

            } catch (Exception e) {
                e.printStackTrace();
            }

            display = activity.getWindowManager().getDefaultDisplay();

        }

        public void prepareInClass() {
            try {
                mediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void auto() {

            mediaPlayer.setVolume(0.0f, 0.0f);
            Log.d(TAG, "auto: autoautoautoautoautoautoautoautoautoautoautoautoauto");
            mediaPlayer.start();
            Log.d(TAG, "auto: startstartstartstartstartstartstartstartstartstart");
            while(true) {
                if (mediaPlayer.isPlaying()) {
                    progressBar.setMax(mediaPlayer.getDuration());
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (true) {
                                    int currentPosition = mediaPlayer.getCurrentPosition();
                                    progressBar.setProgress(currentPosition);
                                    sleep(400);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    break;
                }
            }
            mediaPlayer.setOnInfoListener(this);
        }

        public void pauseVideo() {
            mediaPlayer.pause();

        }

        @Override
        public void onCompletion(MediaPlayer mp) {

        }

        @Override
        public void onPrepared(MediaPlayer mp) {
//        mediaPlayer.prepareAsync();
//        mediaPlayer.setVolume(0.0f, 0.0f);
//        Log.d(TAG, "onPrepared: onPreparedonPreparedonPreparedonPreparedonPreparedonPreparedonPreparedonPrepared");
//        mediaPlayer.setOnInfoListener(this);
//        Log.d(TAG, "auto: autoautoautoautoautoautoautoautoautoautoautoautoauto");
//        mediaPlayer.start();
//        Log.d(TAG, "auto: startstartstartstartstartstartstartstartstartstart");
//        while(true) {
//            if (mediaPlayer.isPlaying()) {
//                progressBar.setMax(mediaPlayer.getDuration());
//                new Thread() {
//                    @Override
//                    public void run() {
//                        try {
//                            while (true) {
//                                int currentPosition = mediaPlayer.getCurrentPosition();
//                                progressBar.setProgress(currentPosition);
//                                sleep(400);
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();
//                break;
//            }
//        }

//        while(true) {
//            if (mediaPlayer.isPlaying()) {
//                progressBar.setMax(mediaPlayer.getDuration());
//                new Thread() {
//                    @Override
//                    public void run() {
//                        try {
//                            while (true) {
//                                int currentPosition = mediaPlayer.getCurrentPosition();
//                                progressBar.setProgress(currentPosition);
//                                sleep(400);
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();
//                break;
//            }
//        }
//        Log.d(TAG, "onPrepared: whilewhilewhilewhile");


        }

        @Override
        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            //when SurfaceView is created,this method will be run
            mediaPlayer.setDisplay(holder);
            Log.d(TAG, "playVideo: surfaceCreatedsurfaceCreatedsurfaceCreatedsurfaceCreatedsurfaceCreated");

//        holder.setFormat(PixelFormat.TRANSLUCENT);

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                surfaceView.setBackgroundColor(Color.TRANSPARENT);
                VideoAdpter.ViewHolderVideo.firstVideoBitmap.setBackgroundColor(Color.TRANSPARENT);
                Log.d(TAG, "onInfo: 我准备好了我准备好了我准备好了我准备好了");
            } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
                surfaceView.setBackground(activity.getResources().getDrawable(R.mipmap.ic_launcher));
                Log.d(TAG, "onInfo: 没有准备好没有准备好没有准备好");
                return false;
            }
            return true;
        }

        public
//        static
        Bitmap getVideoFirstBitmap(final String videoUrl) {
            final Bitmap[] bitmap = {null};
            final MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
//            retriever.setDataSource(videoUrl, new HashMap<String, String>());
                        retriever.setDataSource(videoUrl, new HashMap<String, String>());
                        bitmap[0] = ThumbnailUtils.extractThumbnail(retriever.getFrameAtTime(0), 50, 50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        bitmap[0] = ThumbnailUtils.extractThumbnail(bitmap[0], 50, 50, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                        retriever.release();
                    }
                }
            }).start();
            return bitmap[0];
//        try {
//            retriever.setDataSource(videoUrl, new HashMap<String, String>());
//            retriever.setDataSource(videoUrl, new HashMap<String, String>());
//            bitmap = ThumbnailUtils.extractThumbnail(retriever.getFrameAtTime(0), 50, 50);
//            byte[] bytes = retriever.getEmbeddedPicture();
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
//            BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
//            options.inSampleSize = calcuteImageScale(options, 100, 100);
//            options.inJustDecodeBounds = false;
//            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            retriever.release();
//        }
//        return bitmap;
        }

        public
//        static
        Bitmap saveVideoBitmapToSDP(String imageURL) {
            String filename = ImageLoad.getImageFilePath(imageURL);
            Log.d("saveBitmapToSD", "saveBitmapToSD: The name saved in SD card is " + filename);
            File file = new File(filename);
            FileOutputStream fos = null;
            Bitmap bitmap = null;
            if (file.exists()) {

                bitmap = BitmapFactory.decodeFile(filename);
//            bitmap = getCompressedBitmap(filename, 200, 200);
                Log.d("BITMAPFACTORYRETURN", "saveBitmapToSD: " + BitmapFactory.decodeFile(filename));
                return bitmap;
            } else {

                bitmap = getVideoFirstBitmap(imageURL);
                try {
                    fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                } finally {
                    try {
                        fos.flush();
                        fos.close();
                        return bitmap;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }

        public
//        static
        int calcuteImageScale(BitmapFactory.Options options, int hopeHeihent, int hopeWidth) {
            int imageHeight = options.outHeight;
            int imageWidth = options.outWidth;
            int scale = 1;
            if (imageHeight > hopeHeihent || imageWidth > hopeWidth) {
                final int heightScale = Math.round((float) imageHeight / (float) hopeHeihent);
                final int widthScale = Math.round((float) imageWidth / (float) hopeWidth);
                scale = heightScale < widthScale ? heightScale : widthScale;
            }
            return scale;
        }
        public boolean isPlaying() {
            return mediaPlayer.isPlaying();
        }
    }

    }
