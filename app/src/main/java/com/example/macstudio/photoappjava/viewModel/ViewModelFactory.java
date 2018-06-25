package com.example.macstudio.photoappjava.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.macstudio.photoappjava.db.PhotoRepository;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private PhotoRepository mRepository;
    private SharedPreferences mSharedPreferences;

    @Inject
    public ViewModelFactory(@NonNull final PhotoRepository repository,
                            @NonNull final SharedPreferences sharedPreferences) {
        mRepository = repository;
        mSharedPreferences = sharedPreferences;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SharedViewModel.class)) {
            return (T) new SharedViewModel(mRepository, mSharedPreferences);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
