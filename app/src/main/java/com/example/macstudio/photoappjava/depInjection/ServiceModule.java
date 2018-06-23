package com.example.macstudio.photoappjava.depInjection;

import com.example.macstudio.photoappjava.db.PhotoService;

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
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    PhotoService providePhotoService(Retrofit retrofit) {
        return retrofit.create(PhotoService.class);
    }
}
