package com.example.macstudio.photoappjava.networking.models;

public class PhotoItem {
    private Likes likes;
    private String media_id;
    private User user;
    private PhotoImages images;
    private String id;
    private boolean user_has_liked;
    private String created_time;

    public Likes getLikes() {
        return likes;
    }

    public String getMedia_id() {
        return media_id;
    }

    public User getUser() {
        return user;
    }

    public PhotoImages getImages() {
        return images;
    }

    public String getId() {
        return id;
    }

    public boolean isUser_has_liked() {
        return user_has_liked;
    }

    public String getCreated_time() {
        return created_time;
    }
}
