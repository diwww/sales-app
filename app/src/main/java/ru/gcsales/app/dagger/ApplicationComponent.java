package ru.gcsales.app.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application dagger component.
 */
@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class,
        NetworkModule.class, DatabaseModule.class})
public interface ApplicationComponent {

}
