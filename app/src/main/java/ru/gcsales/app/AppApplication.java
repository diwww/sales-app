package ru.gcsales.app;

import android.app.Application;

import ru.gcsales.app.injection.ApplicationComponent;
import ru.gcsales.app.injection.DaggerApplicationComponent;

public class AppApplication extends Application {

    private static ApplicationComponent sApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationComponent = DaggerApplicationComponent.create();
    }

    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }
}
