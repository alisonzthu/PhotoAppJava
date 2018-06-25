package com.example.macstudio.photoappjava.depInjection;

import com.example.macstudio.photoappjava.ui.PhotoFeedActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract PhotoFeedActivity contributeMainActivity();
}
