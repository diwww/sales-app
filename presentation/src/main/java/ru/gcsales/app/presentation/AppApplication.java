package ru.gcsales.app.presentation;

import android.app.Application;

import ru.gcsales.app.presentation.di.ApplicationComponent;
import ru.gcsales.app.presentation.di.ApplicationModule;
import ru.gcsales.app.presentation.di.DaggerApplicationComponent;

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
