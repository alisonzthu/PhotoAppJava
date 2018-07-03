package com.example.macstudio.photoappjava.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.macstudio.photoappjava.db.model.User;

@Entity(tableName="user")
public class UserEntity implements User {
    @PrimaryKey
    private String id;
    private String profilePicture;
    private String fullName;
    private String username;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getProfilePicture() {
        return profilePicture;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getUserName() {
        return username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
