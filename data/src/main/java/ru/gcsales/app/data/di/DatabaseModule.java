package ru.gcsales.app.data.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.data.AppDatabase;

/**
 * Dagger module which provides Room database.
 *
 * @author Maxim Surovtsev
 * Created on 8/17/18
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration()
                .build();
    }
}
