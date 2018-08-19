package ru.gcsales.app.presentation.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.gcsales.app.data.di.DatabaseModule;
import ru.gcsales.app.data.di.NetworkModule;
import ru.gcsales.app.data.di.RepositoryModule;
import ru.gcsales.app.presentation.presenter.LoginPresenter;
import ru.gcsales.app.presentation.presenter.ItemsPresenter;
import ru.gcsales.app.presentation.presenter.ShoppingListPresenter;
import ru.gcsales.app.presentation.presenter.ShoppingListsPresenter;
import ru.gcsales.app.presentation.presenter.ShopsPresenter;

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class,
        NetworkModule.class, DatabaseModule.class})
public interface ApplicationComponent {
    void inject(ShopsPresenter presenter);

    void inject(ItemsPresenter presenter);

    void inject(LoginPresenter presenter);

    void inject(ShoppingListsPresenter presenter);

    void inject(ShoppingListPresenter presenter);
}
