package ru.gcsales.app.injection;

import javax.inject.Singleton;

import dagger.Component;
import ru.gcsales.app.presentation.mvp.presenter.LoginPresenter;
import ru.gcsales.app.presentation.mvp.presenter.ProductsPresenter;
import ru.gcsales.app.presentation.mvp.presenter.ShoppingListPresenter;
import ru.gcsales.app.presentation.mvp.presenter.ShoppingListsPresenter;
import ru.gcsales.app.presentation.mvp.presenter.ShopsPresenter;
import ru.gcsales.app.presentation.ui.activity.ShoppingListActivity;

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class, UseCaseModule.class,
        NetworkModule.class, DatabaseModule.class})
public interface ApplicationComponent {
    void inject(ShopsPresenter presenter);

    void inject(ProductsPresenter presenter);

    void inject(LoginPresenter presenter);

    void inject(ShoppingListsPresenter presenter);

    void inject(ShoppingListPresenter presenter);
}
