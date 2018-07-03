package com.example.macstudio.photoappjava.db;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.macstudio.photoappjava.db.dao.PhotoDao;
import com.example.macstudio.photoappjava.db.dao.UserDao;
import com.example.macstudio.photoappjava.db.entity.PhotoEntity;
import com.example.macstudio.photoappjava.db.entity.UserEntity;
import com.example.macstudio.photoappjava.db.model.User;
import com.example.macstudio.photoappjava.networking.models.PhotoItem;

import java.util.List;

@Database(entities = {PhotoEntity.class, UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase mInstance;

    public static final String DATABASE_NAME="photo-app-db";

    public abstract PhotoDao photoDao();
    public abstract UserDao userDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context) {
        if (mInstance == null) {
            synchronized (AppDatabase.class) {
                if(mInstance == null) {
                    mInstance = buildDatabase(context.getApplicationContext());
                    mInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    private static AppDatabase buildDatabase(@NonNull final Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        AppDatabase database = AppDatabase.getInstance(appContext);
                        // make api calls to get photos and user info
//                        insertData();
                        database.setDatabaseCreated();
                    }
                }).build();
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    /**
     * can be used by repository to insert api data to db
     */
    public static void insertData(@NonNull final AppDatabase database, @Nullable final List<PhotoItem> photoItems, @Nullable final User user) {
        database.runInTransaction(new Runnable() {
            @Override
            public void run() {
                if (photoItems != null) {
                    // todo: check if replace strategy is used when having conflicts
                    database.photoDao().insertAll(photoItems);
                }
                if (user != null) {
                    database.userDao().insert(user);
                }
            }
        });
    }
}
