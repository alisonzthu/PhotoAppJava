package com.example.macstudio.photoappjava.db.model;

public interface Photo {
    String getId();
    String getMediaId();
    int likeCount();
    String getThumbnailUrl();
    String getStandardResoUrl();
    String getLowResoUrl();
    boolean isUserLiked();
    String getCreatedTime();

}
