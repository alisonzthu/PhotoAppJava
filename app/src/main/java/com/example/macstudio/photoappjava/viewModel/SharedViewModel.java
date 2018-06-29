package com.example.macstudio.photoappjava.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.macstudio.photoappjava.db.PhotoRepository;
import com.example.macstudio.photoappjava.networking.PhotoServiceClient;
import com.example.macstudio.photoappjava.networking.models.PhotoItem;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.macstudio.photoappjava.AppConstants.AUTHORIZATION;

public class SharedViewModel extends ViewModel {

    //todo: I want to refactor this viewmodel so that I can pass an instance of this viewmodel
    // for data binding. I need to separate out PhotoItem instead of a list of them
    @Nullable
    private LiveData<List<PhotoItem>> mPhotoData;
    @NonNull
    private PhotoRepository mPhotoRepository;
//    @NonNull
//    private SharedPreferences mSharedPreferences;
    @NonNull
    private final String mAuthorizationCode;
    // todo: tbd: maybe I can move all api related logic to the repository
    @NonNull
    private PhotoServiceClient mPhotoServiceClient;

    public SharedViewModel(@NonNull final PhotoRepository repository, @NonNull final SharedPreferences sharedPreferences, @NonNull final PhotoServiceClient photoServiceClient) {
        mPhotoRepository = repository;
        mAuthorizationCode = sharedPreferences.getString(AUTHORIZATION, "");
        mPhotoServiceClient = photoServiceClient;
    }

    @NonNull
    public LiveData<List<PhotoItem>> getPhotoDataList() {
        if (mPhotoData == null) {
            mPhotoData = mPhotoRepository.getPhotoData(mAuthorizationCode);
        }
        return mPhotoData;
    }

    // todo: post like through repository here

    public void clickLike(@NonNull final PhotoItem photoItem) {
        Log.d("alison", "viewmodel: like is clicked");
        Log.d("alison", "like status: " + photoItem.isUser_has_liked());
        final boolean userHasLiked = photoItem.isUser_has_liked();
        if (userHasLiked) {
            mPhotoServiceClient.unlikePhoto(mAuthorizationCode, photoItem.getMedia_id()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    Log.d("alison", "onResponse for unlike photo");

                    if(response.isSuccessful()) {
                        Log.d("alison", "unlike photo successful");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Log.d("alison", "unlike photo failure");
                }
            });
        } else {
            mPhotoServiceClient.likePhoto(mAuthorizationCode, photoItem.getMedia_id()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    Log.d("alison","onResponse for like photo");
                    if(response.isSuccessful()) {
                        Log.d("alison", "like photo successful");
                        mPhotoRepository.getPhotoData(mAuthorizationCode);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Log.d("alison", "like photo failure");
                }
            });
        }

    }

    public void openPhotoFragment(@NonNull final PhotoItem photoItem) {
        Log.d("alison","open PhotoFragment");
    }
}
