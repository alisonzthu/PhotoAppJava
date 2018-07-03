package com.example.macstudio.photoappjava.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.macstudio.photoappjava.db.model.Photo;

@Entity(tableName="photos")
public class PhotoEntity implements Photo {
    @PrimaryKey
    private String id;
    private String mediaId;
    private int likeCount;
    private String thumnailUrl;
    private String standardResoUrl;
    private String lowResoUrl;
    private boolean isUserLiked;
    private String createdTime;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getMediaId() {
        return mediaId;
    }

    @Override
    public int likeCount() {
        return likeCount;
    }

    @Override
    public String getThumbnailUrl() {
        return thumnailUrl;
    }

    @Override
    public String getStandardResoUrl() {
        return standardResoUrl;
    }

    @Override
    public String getLowResoUrl() {
        return lowResoUrl;
    }

    @Override
    public boolean isUserLiked() {
        return isUserLiked;
    }

    @Override
    public String getCreatedTime() {
        return createdTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setThumnailUrl(String thumnailUrl) {
        this.thumnailUrl = thumnailUrl;
    }

    public void setStandardResoUrl(String standardResoUrl) {
        this.standardResoUrl = standardResoUrl;
    }

    public void setLowResoUrl(String lowResoUrl) {
        this.lowResoUrl = lowResoUrl;
    }

    public void setUserLiked(boolean userLiked) {
        isUserLiked = userLiked;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
