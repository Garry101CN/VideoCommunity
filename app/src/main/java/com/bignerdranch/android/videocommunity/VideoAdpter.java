package com.bignerdranch.android.videocommunity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bignerdranch.android.imageloadingwan.CircleImageViewWan;
//import com.bignerdranch.android.imageloadingwan.ImageLoad;

import java.util.List;

import static com.bignerdranch.android.videocommunity.VideoAdpter.ViewHolderVideo.firstVideoBitmap;
import static com.bignerdranch.android.videocommunity.VideoAdpter.ViewHolderVideo.progressBar;
import static com.bignerdranch.android.videocommunity.VideoAdpter.ViewHolderVideo.surfaceView;

/**
 * Created by LENOVO on 2017/5/17.
 */

public class VideoAdpter extends RecyclerView.Adapter<MyViewHolder> {
    private List<ContentlistEntity> contentlistEntities;
    private VideoPlayer videoPlayer;
    private Activity activity;
    private static final int SUCCESS = 1;
    private static final int FALIED = 2;
    public static final int IMAGE = 10;
    public static final int VIDEO =41;
    public static final int TEXT = 29;

    static class ViewHolderVideo extends MyViewHolder {

        CircleImageViewWan userAvatar;
        TextView userName;
        TextView createTime;
        TextView title;
        TextView followNum;
        TextView hateNum;
        TextView toWeChat;
        static ImageView firstVideoBitmap;
        static SurfaceView surfaceView;
        static ProgressBar progressBar;

        public ViewHolderVideo(View v) {
            super(v);
            surfaceView = (SurfaceView) v.findViewById(R.id.video_surface);
            progressBar = (ProgressBar) v.findViewById(R.id.video_progressbar);
            firstVideoBitmap = (ImageView) v.findViewById(R.id.video_image_firstbitmap);
            userAvatar = (CircleImageViewWan) v.findViewById(R.id.video_user_avatar);
            userName = (TextView) v.findViewById(R.id.video_user_name);
            createTime = (TextView) v.findViewById(R.id.video_create_time);
            title = (TextView) v.findViewById(R.id.video_title);
            followNum = (TextView) v.findViewById(R.id.video_text_follow);
            hateNum  =(TextView) v.findViewById(R.id.video_text_hate);
            toWeChat = (TextView) v.findViewById(R.id.video_text_wechat);
        }

        @Override
        public void onBindImageViewInHolder(final Activity activity, final ContentlistEntity contentlistEntity, VideoPlayer player) {
            super.onBindImageViewInHolder(activity, contentlistEntity, player);
            surfaceView.setZOrderOnTop(true);
            surfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            ImageLoad.getCircleImage(activity, contentlistEntity.getProfile_image(), userAvatar);
            userName.setText(contentlistEntity.getName());
            createTime.setText(contentlistEntity.getCreate_time());
            title.setText(contentlistEntity.getText());
            followNum.setText(contentlistEntity.getLove());
            hateNum.setText(contentlistEntity.getHate());

        }
    }

    static class ViewHolderText extends MyViewHolder {
        CircleImageViewWan userAvatar;
        TextView userName;
        TextView createTime;
        TextView title;
        TextView followNum;
        TextView hateNum;
        TextView toWeChat;
        Activity activity;

        public ViewHolderText(View v, Activity activity) {
            super(v);
            this.activity = activity;
            userAvatar = (CircleImageViewWan) v.findViewById(R.id.image_user_avatar);
            userName = (TextView) v.findViewById(R.id.image_user_name);
            createTime = (TextView) v.findViewById(R.id.image_create_time);
            title = (TextView) v.findViewById(R.id.image_title);
            followNum = (TextView) v.findViewById(R.id.image_text_follow);
            hateNum  =(TextView) v.findViewById(R.id.image_text_hate);
            toWeChat = (TextView) v.findViewById(R.id.image_text_wechat);
        }

        @Override
        public void onBindImageViewInHolder(Activity activity, ContentlistEntity contentlistEntity, VideoPlayer player) {
            super.onBindImageViewInHolder(activity, contentlistEntity, player);
            ImageLoad.getCircleImage(activity, contentlistEntity.getProfile_image(), userAvatar);
            userName.setText(contentlistEntity.getName());
            createTime.setText(contentlistEntity.getCreate_time());
            title.setText(contentlistEntity.getText());
            followNum.setText(contentlistEntity.getLove());
            hateNum.setText(contentlistEntity.getHate());
        }
    }

    static class ViewHolderImage extends MyViewHolder {

        CircleImageViewWan userAvatar;
        TextView userName;
        TextView createTime;
        TextView title;
        ImageView image3;
        TextView followNum;
        TextView hateNum;
        TextView toWeChat;
        Activity activity;

        public ViewHolderImage(View v, Activity activity) {
            super(v);
            this.activity = activity;
            userAvatar = (CircleImageViewWan) v.findViewById(R.id.image_user_avatar);
            userName = (TextView) v.findViewById(R.id.image_user_name);
            createTime = (TextView) v.findViewById(R.id.image_create_time);
            title = (TextView) v.findViewById(R.id.image_title);
            image3 = (ImageView) v.findViewById(R.id.image_imageView3);
            followNum = (TextView) v.findViewById(R.id.image_text_follow);
            hateNum  =(TextView) v.findViewById(R.id.image_text_hate);
            toWeChat = (TextView) v.findViewById(R.id.image_text_wechat);
        }

        @Override
        public void onBindImageViewInHolder(Activity activity, ContentlistEntity contentlistEntity, VideoPlayer player) {
            super.onBindImageViewInHolder(activity, contentlistEntity, player);
            ImageLoad.getCircleImage(activity, contentlistEntity.getProfile_image(), userAvatar);
            userName.setText(contentlistEntity.getName());
            createTime.setText(contentlistEntity.getCreate_time());
            title.setText(contentlistEntity.getText());
            ImageLoad.getImage(activity, contentlistEntity.getImage3(), image3);
            followNum.setText(contentlistEntity.getLove());
            hateNum.setText(contentlistEntity.getHate());
        }
    }

    public VideoAdpter(List<ContentlistEntity> contentlistEntities, Activity activity) {
        this.contentlistEntities = contentlistEntities;
        this.activity = activity;
    }

    public void prepareInClass() {
            videoPlayer.prepareInClass();
    }

    public void continuePlay(){
        videoPlayer.continuePlay();
    }

    public void autoPlay() {
        videoPlayer.auto();
        
    }
    
    public void pauseVideo() {
        videoPlayer.pauseVideo();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIDEO) {
            View videoView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.video_list, parent, false);
            final ViewHolderVideo videoHolder = new ViewHolderVideo(videoView);
            videoHolder.toWeChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = videoHolder.getAdapterPosition();
                    Intent intent = new Intent(activity, WeChatWeb.class);
                    intent.putExtra("WeChatUrl", contentlistEntities.get(position).getWeixin_url());
                    activity.startActivity(intent);
                }
            });
            return videoHolder;
        } else if (viewType == IMAGE) {
            View imageView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_lsit, parent, false);
            final ViewHolderImage imageHolder = new ViewHolderImage(imageView, activity);
            imageHolder.toWeChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = imageHolder.getAdapterPosition();
                    Intent intent = new Intent(activity, WeChatWeb.class);
                    intent.putExtra("WeChatUrl", contentlistEntities.get(position).getWeixin_url());
                    activity.startActivity(intent);
                }
            });
            return imageHolder;
        } else if (viewType == TEXT) {
            View textView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.text_list, parent, false);
            final ViewHolderText textHolder = new ViewHolderText(textView, activity);
            textHolder.toWeChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = textHolder.getAdapterPosition();
                    Intent intent = new Intent(activity, WeChatWeb.class);
                    intent.putExtra("WeChatUrl", contentlistEntities.get(position).getWeixin_url());
                    activity.startActivity(intent);

                }
            });
            return textHolder;
        }
        return null;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (contentlistEntities.get(position).getType() == VIDEO) {
            firstVideoBitmap.setImageBitmap(VideoPlayer.getVideoFirstBitmap(contentlistEntities.get(position).getVideo_uri()));
        }
            holder.onBindImageViewInHolder(activity, contentlistEntities.get(position), videoPlayer);
            if (contentlistEntities.get(position).getType() == VIDEO) {
                videoPlayer = new VideoPlayer(activity, surfaceView, progressBar);
                videoPlayer.playVideo(contentlistEntities.get(position).getVideo_uri());
//                videoPlayer.prepareInClass();
                if (position == 0 && contentlistEntities.get(position).getVideo_uri() != null) {
                    videoPlayer.prepareInClass();
                    videoPlayer.auto();
                    contentlistEntities.get(0).setIfPlay(true);
                }
                surfaceView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, VideoWithController.class);
                        intent.putExtra("url", contentlistEntities.get(position).getVideo_uri());
                        intent.putExtra("userName", contentlistEntities.get(position).getName());
                        intent.putExtra("userAvatar", contentlistEntities.get(position).getProfile_image());
                        intent.putExtra("title", contentlistEntities.get(position).getText());
                        activity.startActivity(intent);
                    }
                });
            }
        }

    @Override
    public int getItemCount() {
        return contentlistEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
        return contentlistEntities.get(position).getType();
    }
}