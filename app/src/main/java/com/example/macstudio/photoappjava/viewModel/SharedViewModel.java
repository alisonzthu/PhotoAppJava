package com.example.macstudio.photoappjava.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.macstudio.photoappjava.db.PhotoRepository;
import com.example.macstudio.photoappjava.networking.models.PhotoData;

import java.util.List;

import static com.example.macstudio.photoappjava.AppConstants.AUTHORIZATION;

public class SharedViewModel extends ViewModel {

    @Nullable
    private LiveData<List<PhotoData>> mPhotoData;
    @NonNull
    private PhotoRepository mPhotoRepository;
    @NonNull
    private SharedPreferences mSharedPreferences;

    public SharedViewModel(@NonNull final PhotoRepository repository, @NonNull final SharedPreferences sharedPreferences) {
        mPhotoRepository = repository;
        mSharedPreferences = sharedPreferences;
    }

    @NonNull
    public LiveData<List<PhotoData>> getPhotoDataList() {
        if (mPhotoData == null) {
            final String authorization = getAuthorizationCode();
            mPhotoData = mPhotoRepository.getPhotoData(authorization);
        }
        return mPhotoData;
    }

    @NonNull
    private String getAuthorizationCode() {
        return mSharedPreferences.getString(AUTHORIZATION, "");
    }
}
