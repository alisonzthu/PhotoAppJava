package com.example.macstudio.photoappjava.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.macstudio.photoappjava.networking.PhotoServiceClient;
import com.example.macstudio.photoappjava.networking.models.PhotoAppDataResponse;
import com.example.macstudio.photoappjava.networking.models.PhotoData;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepository {

    private PhotoServiceClient mPhotoServiceClient;

    @Inject
    public PhotoRepository(@NonNull final PhotoServiceClient client) {
        mPhotoServiceClient = client;
    }

    public LiveData<List<PhotoData>> getPhotoData(@NonNull final String authorizationCode) {
        final MutableLiveData<List<PhotoData>> photoDataList = new MutableLiveData<>();
        mPhotoServiceClient.photoForUser(authorizationCode).enqueue(new Callback<PhotoAppDataResponse>() {
            @Override
            public void onResponse(@NonNull Call<PhotoAppDataResponse> call, @NonNull Response<PhotoAppDataResponse> response) {
                if(response.isSuccessful()) {
                    //todo: set data to viewmodel
                    Log.d("alison", "response successful");
                    final PhotoAppDataResponse theResponse = response.body();
                    if (theResponse != null) {
                        final List<PhotoData> photoData = theResponse.getData();
                        photoDataList.setValue(photoData);
                    }
                } else {
                    Log.d("alison", "response failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotoAppDataResponse> call, @NonNull Throwable t) {
                Log.d("alison", "onFailure");
                //todo: display failure page
            }
        });
        return photoDataList;
    }
}
