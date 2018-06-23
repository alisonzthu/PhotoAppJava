package com.example.macstudio.photoappjava.depInjection;

import com.example.macstudio.photoappjava.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules={AndroidInjectionModule.class,
                    AppModule.class,
                    BuildersModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MyApplication myApplication);

        AppComponent build();
    }

    void inject(MyApplication myApplication);
}
