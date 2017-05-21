package com.bignerdranch.android.videocommunity;

import android.Manifest;
import android.content.Intent;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;

import com.bignerdranch.android.imageloadingwan.CallBackListener;
import com.bignerdranch.android.imageloadingwan.CheckPermission;
import com.bignerdranch.android.imageloadingwan.HttpMethod;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.bignerdranch.android.videocommunity.R.id.recyclerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View current;
    private View previous;
    private boolean isPlaying = false;
    private HttpMethod httpMethod;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ImageButton download;
    private boolean first = true;
    private LinearLayoutManager manager;
    private List<ContentlistEntity> contentlistEntities;
    private VideoAdpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckPermission.cheakActivityPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, CheckPermission.UNABLE_START);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        toolbar = (Toolbar) findViewById(R.id.main_toolBar);
        download = (ImageButton) findViewById(R.id.main_download);

        download.setOnClickListener(this);
        manager = new LinearLayoutManager(MainActivity.this);
        manager.setInitialPrefetchItemCount(10);
        recyclerView.setLayoutManager(manager);
        toolbar.setTitle("百思不得姐");
        setSupportActionBar(toolbar);

        httpMethod = new HttpMethod();
        httpMethod.httpRequest("http://route.showapi.com/255-1?showapi_appid=38522&showapi_sign=30eec5795fa84a1187d0330e971149d6", new CallBackListener() {
            @Override
            public void onFinish(String response) {
                parseDtaBeans(response);
            }

            @Override
            public void onError(Exception e) {
                Log.d("MainActivity", "onError: 在网路请求时出错");
                e.printStackTrace();
            }
        });
    }
    public void parseDtaBeans(String response) {
        try {
            JSONObject allObject = new JSONObject(response);
            String showapi_res_bodyObject= allObject.getString("showapi_res_body");
            ContentlistEntity dataBeans = new ContentlistEntity();
            JSONObject pagebeanObject = new JSONObject(showapi_res_bodyObject);
            String pagebean = pagebeanObject.getString("pagebean");
            JSONObject contentlistO = new JSONObject(pagebean);
            final String contentlist = contentlistO.getString("contentlist");
            JSONArray contentlistArray = new JSONArray(contentlist);
            contentlistEntities = new ArrayList<>();
            for (int i = 0; i < contentlistArray.length(); i++) {
                JSONObject contentlistObject = contentlistArray.getJSONObject(i);
                ContentlistEntity content = new ContentlistEntity();
                String text = contentlistObject.getString("text");
                String hate = contentlistObject.getString("hate");
                String videotime = contentlistObject.getString("videotime");
                String voicetime = contentlistObject.getString("voicetime");
                String weixin_url = contentlistObject.getString("weixin_url");
                String profile_image = contentlistObject.getString("profile_image");
                String width = contentlistObject.getString("width");
                String type = contentlistObject.getString("type");
                String id = contentlistObject.getString("id");
                String love = contentlistObject.getString("love");
                String height = contentlistObject.getString("height");
                String name = contentlistObject.getString("name");
                String create_time = contentlistObject.getString("create_time");
                if (!contentlistObject.isNull("video_uri")) {
                    Log.d("mainActivity", "parseDtaBeans: video_urivideo_urivideo_urivideo_urivideo_uri");
                    String voiceuri = contentlistObject.getString("voiceuri");
                    String video_uri = contentlistObject.getString("video_uri");
                    String voicelength = contentlistObject.getString("voicelength");
                    content.setVoiceuri(voiceuri);
                    content.setVideo_uri(video_uri);
                    content.setVideotime(videotime);
                    content.setVoicelength(voicelength);
                } else if (!contentlistObject.isNull("image0")){
                    Log.d("mainActivity", "parseDtaBeans: image0image0image0image0image0image0");
                    String image0 = contentlistObject.getString("image0");
                    Log.d("mainActivity", "parseDtaBeans: " + image0);
                    String image1 = contentlistObject.getString("image1");
                    String image2 = contentlistObject.getString("image2");
                    String image3 = contentlistObject.getString("image3");
                    content.setImage0(image0);
                    content.setImage1(image1);
                    content.setImage3(image3);
                    content.setImage2(image2);
                }

                content.setText(text);
                content.setHate(hate);
                content.setVideotime(videotime);
                content.setVoicetime(voicetime);
                content.setWeixin_url(weixin_url);
                content.setProfile_image(profile_image);
                content.setWidth(width);
                content.setType(type);
                content.setId(id);
                content.setLove(love);
                content.setHeight(height);
                content.setName(name);
                content.setCreate_time(create_time);
                contentlistEntities.add(content);
//                if (content == null)
                Log.d("MainActivity", "parseDtaBeans: contentlistEntitiescontentlistEntitiescontentlistEntities" + content.getLove());
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adpter = new VideoAdpter(contentlistEntities, MainActivity.this);
                    recyclerView.setAdapter(adpter);

                }
            });


            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    switch (newState) {
                        case RecyclerView.SCROLL_STATE_IDLE:
                            final int position = manager.findLastVisibleItemPosition();
                            current = manager.findViewByPosition(position);
                            Log.d(",main", "onScrollStateChanged:---------------------- " + position);
                            if (position > 0 && contentlistEntities.get(position).getVideo_uri() != "http://mvideo.spriteapp.cn/video/2017/0515/591992c213abe_wpc.mp4") {
                                if (contentlistEntities.get(position).getType() == VideoAdpter.VIDEO) {
                                    adpter.prepareInClass();
                                    adpter.autoPlay();
                                    ContentlistEntity.ifPlay = true;
                                    isPlaying = true;
                                }
                            }
                            break;
                        case RecyclerView.SCROLL_STATE_DRAGGING:
                            if (first && ContentlistEntity.ifPlay == true) {
                                adpter.pauseVideo();
                                first = false;
                            } else if (current != previous && isPlaying ) {
                                adpter.pauseVideo();
                                isPlaying = false;
                            }
                            break;
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
//                    if (dy < 0) {
//                        adpter.continuePlay();
//                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_download:
                Intent intent = new Intent(MainActivity.this, DownLoadPlay.class);
                startActivity(intent);
        }
    }
}
