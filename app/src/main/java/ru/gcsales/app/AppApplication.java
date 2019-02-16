package ru.gcsales.app;

import android.app.Application;
import android.content.Context;

import io.reactivex.subjects.PublishSubject;
import ru.gcsales.app.dagger.ApplicationComponent;
import ru.gcsales.app.dagger.ApplicationModule;
import ru.gcsales.app.dagger.DaggerApplicationComponent;
import ru.gcsales.app.domain.model.ShoppingListEntry;

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

    // FIXME: replace context with dagger

    private static Context sContext;

    private static final PublishSubject<ShoppingListEntry> subject = PublishSubject.create();

    public static PublishSubject<ShoppingListEntry> getSubject() {
        return subject;
    }

    public static Context getContext() {
        return sContext;
    }
}
