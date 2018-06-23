package com.example.macstudio.photoappjava.depInjection;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.macstudio.photoappjava.MyApplication;

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
