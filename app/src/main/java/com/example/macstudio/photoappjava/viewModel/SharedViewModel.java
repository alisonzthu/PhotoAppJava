package com.example.macstudio.photoappjava.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.macstudio.photoappjava.networking.PhotoServiceClient;
import com.example.macstudio.photoappjava.networking.models.PhotoAppDataResponse;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<PhotoAppDataResponse> mPhotoAppResponseMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<PhotoAppDataResponse> getPhotoAppResponseMutableLiveData() {
        return mPhotoAppResponseMutableLiveData;
    }

    private void loadPhotos(final String Authorization) {
//        PhotoServiceClient client =
    }
}
