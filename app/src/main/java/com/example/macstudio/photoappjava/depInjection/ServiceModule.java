package com.example.macstudio.photoappjava.depInjection;

import android.arch.lifecycle.ViewModelProvider;
import android.content.SharedPreferences;

import com.example.macstudio.photoappjava.db.PhotoRepository;
import com.example.macstudio.photoappjava.networking.PhotoServiceClient;
import com.example.macstudio.photoappjava.viewModel.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit
                .Builder()
                .baseUrl("https://kqlpe1bymk.execute-api.us-west-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    PhotoServiceClient providePhotoServiceClient(Retrofit retrofit) {
        return retrofit.create(PhotoServiceClient.class);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(PhotoRepository myRepository, SharedPreferences sharedPreferences) {
        return new ViewModelFactory(myRepository, sharedPreferences);
    }
}
