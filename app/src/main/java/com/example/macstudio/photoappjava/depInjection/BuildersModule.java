package com.example.macstudio.photoappjava.depInjection;

import com.example.macstudio.photoappjava.ui.PhotoFeedActivity;
import com.example.macstudio.photoappjava.ui.PhotoListFragment;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
public abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract PhotoFeedActivity bindMainActivity();
    // Personal note: this is necessary to not have a compile error
    @ContributesAndroidInjector
    abstract PhotoListFragment bindPhotoListFragment();
}
