package ru.gcsales.app;

import android.app.Application;

import ru.gcsales.app.dagger.ApplicationComponent;
import ru.gcsales.app.dagger.ApplicationModule;
import ru.gcsales.app.dagger.DaggerApplicationComponent;

public class AppApplication extends Application {

    private static ApplicationComponent sApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }
}
