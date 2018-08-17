package ru.gcsales.app.injection;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.data.AppDatabase;

/**
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
@Module
public class DatabaseModule {
    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration()
                .build();
    }
}
