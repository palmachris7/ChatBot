package com.upc.chatbot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsResponse {

    @SerializedName("newsId")
    @Expose
    private int newsId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("imagePicture")
    @Expose
    private String imagePicture;


    @SerializedName("userDto")
    @Expose
    private UserDto userDto;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getImagePicture() {
        return imagePicture;
    }

    public void setImagePicture(String imagePicture) {
        this.imagePicture = imagePicture;
    }


}
