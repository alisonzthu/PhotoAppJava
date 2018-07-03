package com.example.macstudio.photoappjava.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.macstudio.photoappjava.networking.models.PhotoItem;

import java.util.List;

@Dao
public interface PhotoDao {
    @Query("SELECT * FROM photos")
    LiveData<List<PhotoItem>> loadAllPhotos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PhotoItem> photoItems);

    @Query("SELECT * FROM photos WHERE mediaId = :mediaId")
    PhotoItem loadPhoto(String mediaId);
}