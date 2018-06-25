package com.example.macstudio.photoappjava.networking.models;

public class PhotoData {
    private Likes likes;
    private User user;
    private PhotoImages images;
    private String id;
    private boolean user_has_liked;
    private String created_time;

    public Likes getLikes() {
        return likes;
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
