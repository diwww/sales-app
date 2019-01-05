package ru.gcsales.app;

import android.app.Application;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

import ru.gcsales.app.dagger.ApplicationComponent;
import ru.gcsales.app.dagger.ApplicationModule;
import ru.gcsales.app.dagger.DaggerApplicationComponent;

public class AppApplication extends Application {

    private static ApplicationComponent sApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }

    // TODO: remove

    private static Context sContext;

    public static String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = sContext.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
