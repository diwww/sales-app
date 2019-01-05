package ru.gcsales.app.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.data.repository.ShopsRepositoryImpl;
import ru.gcsales.app.domain.repository.ShopsRepository;

/**
 * Dagger module which provides repository implementations.
 *
 * @author Maxim Surovtsev
 * Created on 8/17/18
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ShopsRepository provideShopRepository() {
        return new ShopsRepositoryImpl();
    }
}
