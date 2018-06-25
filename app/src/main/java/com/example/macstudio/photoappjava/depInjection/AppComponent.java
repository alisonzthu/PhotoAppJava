package com.example.macstudio.photoappjava.depInjection;

import com.example.macstudio.photoappjava.MyApplication;
import com.example.macstudio.photoappjava.ui.PhotoFeedActivity;
import com.example.macstudio.photoappjava.ui.PhotoListFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
                    BuildersModule.class,
                    AppModule.class,
                    ServiceModule.class,
                    AndroidInjectionModule.class
})
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MyApplication myApplication);

        AppComponent build();
    }

    void inject(MyApplication myApplication);
    void inject(PhotoFeedActivity args);
    void inject(PhotoListFragment args);

}
