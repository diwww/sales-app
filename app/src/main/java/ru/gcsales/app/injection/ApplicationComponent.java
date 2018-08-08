package ru.gcsales.app.injection;

import javax.inject.Singleton;

import dagger.Component;
import ru.gcsales.app.presentation.mvp.presenter.LoginPresenter;
import ru.gcsales.app.presentation.mvp.presenter.ProductListPresenter;
import ru.gcsales.app.presentation.mvp.presenter.ShopsPresenter;

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class, UseCaseModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(ShopsPresenter presenter);

    void inject(ProductListPresenter presenter);

    void inject(LoginPresenter presenter);
}
