package com.bignerdranch.android.videocommunity;

import java.util.List;

/**
 * Created by LENOVO on 2017/5/19.
 */

public  class ContentlistEntity {
    /**
     * text :
     别动！我在数一共有几层……

     * hate : 29
     * videotime : 0
     * voicetime : 0
     * weixin_url : http://m.budejie.com/detail-25026251.html/
     * profile_image : http://wimg.spriteapp.cn/profile/large/2017/03/28/58d9cf7ba2de4_mini.jpg
     * width : 0
     * voiceuri :
     * type : 10
     * image0 : http://wimg.spriteapp.cn/ugc/2017/05/19/591e4f5f9b1b9.gif
     * id : 25026251
     * love : 111
     * image2 : http://wimg.spriteapp.cn/ugc/2017/05/19/591e4f5f9b1b9.gif
     * image1 : http://wimg.spriteapp.cn/ugc/2017/05/19/591e4f5f9b1b9.gif
     * height : 0
     * name : 幽默搞笑GIF图
     * create_time : 2017-05-19 18:38:01
     * image3 : http://wimg.spriteapp.cn/ugc/2017/05/19/591e4f5f9b1b9.gif
     * video_uri : http://mvideo.spriteapp.cn/video/2017/0519/591e70760b2c9_wpc.mp4
     * voicelength : 0
     */

    private String text ;
    private String hate;
    private String videotime;
    private String voicetime;
    private String weixin_url;
    private String profile_image;
    private String width;
    private String voiceuri = null;
    private String type;
    private String image0 = null;
    private String id;
    private String love;
    private String image2 = null;
    private String image1 = null;
    private String height;
    private String name;
    private String create_time;
    private String image3 = null;
    private String video_uri = null;
    private String voicelength = null;
    public static boolean ifPlay = false;
    public static boolean downloaded = false;

    @Override
    public int hashCode() {
        return 1;
    }

    public boolean isIfPlay() {
        return ifPlay;
    }

    public void setIfPlay(boolean ifPlay) {
        this.ifPlay = ifPlay;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setHate(String hate) {
        this.hate = hate;
    }

    public void setVideotime(String videotime) {
        this.videotime = videotime;
    }

    public void setVoicetime(String voicetime) {
        this.voicetime = voicetime;
    }

    public void setWeixin_url(String weixin_url) {
        this.weixin_url = weixin_url;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setVoiceuri(String voiceuri) {
        this.voiceuri = voiceuri;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImage0(String image0) {
        this.image0 = image0;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public void setVideo_uri(String video_uri) {
        this.video_uri = video_uri;
    }

    public void setVoicelength(String voicelength) {
        this.voicelength = voicelength;
    }

    public String getText() {
        return text;
    }

    public String getHate() {
        return hate;
    }

    public int getVideotime() {
        int videotimeNum = Integer.parseInt(videotime);
        return videotimeNum;
    }

    public int getVoicetime() {
        int voicetimeNum = Integer.parseInt(voicetime);
        return voicetimeNum;
    }

    public String getWeixin_url() {
        return weixin_url;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public int getWidth() {
        int widthNum = Integer.parseInt(width);
        return widthNum;
    }

    public String getVoiceuri() {
        return voiceuri;
    }

    public int getType() {
        int typeNum = Integer.parseInt(type);
        return typeNum;
    }

    public String getImage0() {
        return image0;
    }

    public int getId() {
        int idNum = Integer.parseInt(id);
        return idNum;
    }

    public String getLove() {
        return love;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage1() {
        return image1;
    }

    public int getHeight() {
        int heightNum = Integer.parseInt(height);
        return heightNum;
    }

    public String getName() {
        return name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getImage3() {
        return image3;
    }

    public String getVideo_uri() {
        return video_uri;
    }

    public int  getVoicelength() {
        int voicelengthNum = Integer.parseInt(voicelength);
        return voicelengthNum;
    }
}
