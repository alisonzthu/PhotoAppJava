package com.example.macstudio.photoappjava.depInjection;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.macstudio.photoappjava.MyApplication;
import com.example.macstudio.photoappjava.db.PhotoRepository;
import com.example.macstudio.photoappjava.networking.PhotoServiceClient;
import com.example.macstudio.photoappjava.viewModel.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Application provideApplication(MyApplication app){
        return app;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    PhotoRepository providesRepository(PhotoServiceClient serviceClient) {
        return new PhotoRepository(serviceClient);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(PhotoRepository myRepository, SharedPreferences sharedPreferences) {
        return new ViewModelFactory(myRepository, sharedPreferences);
    }

    //    @Provides
//    @Singleton
//    fun provideDatabase(app: MyApplication): AppDatabase {
////        TODO
//    }
//
//    @Provides
//    @Singleton
//    fun providePasswordDal(db: AppDatabase): PasswordDao {
//        //todo
//    }
//
//    @Provides
//    @Singleton
//    fun provideCategoryDao(db: AppDatabase): CategoryDao {
//        // todo
//    }
}
