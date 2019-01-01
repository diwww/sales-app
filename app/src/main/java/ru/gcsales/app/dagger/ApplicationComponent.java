package ru.gcsales.app.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.gcsales.app.presentation.presenter.CategoriesPresenter;
import ru.gcsales.app.presentation.presenter.HomePresenter;
import ru.gcsales.app.presentation.presenter.LoginPresenter;
import ru.gcsales.app.presentation.presenter.ItemsPresenter;
import ru.gcsales.app.presentation.presenter.RegisterPresenter;
import ru.gcsales.app.presentation.presenter.ShoppingListPresenter;
import ru.gcsales.app.presentation.presenter.ShoppingListsPresenter;
import ru.gcsales.app.presentation.presenter.ShopsPresenter;

/**
 * Application dagger component.
 */
@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class,
        NetworkModule.class, DatabaseModule.class})
public interface ApplicationComponent {

    void inject(LoginPresenter presenter);

    void inject(RegisterPresenter presenter);

    void inject(ShopsPresenter presenter);

    void inject(ItemsPresenter presenter);

    void inject(ShoppingListsPresenter presenter);

    void inject(ShoppingListPresenter presenter);

    void inject(HomePresenter presenter);

    void inject(CategoriesPresenter presenter);
}
