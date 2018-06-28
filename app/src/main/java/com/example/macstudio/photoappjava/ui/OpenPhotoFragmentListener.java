package com.example.macstudio.photoappjava.ui;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.example.macstudio.photoappjava.networking.models.PhotoData;

public class OpenPhotoFragmentListener {
//    @NonNull final PhotoData photoItem, @NonNull final ImageView imageView
    public void openPhotoFragment(@NonNull final PhotoData photoItem) {
        //todo: ask the view to show a single fragment of photoItem
        Log.d("alison", "openPhotoFragmentListener");
    }
}
