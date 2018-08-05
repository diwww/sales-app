package ru.gcsales.app.injection;

import javax.inject.Singleton;

import dagger.Component;
import ru.gcsales.app.presentation.mvp.presenter.ProductListPresenter;
import ru.gcsales.app.presentation.mvp.presenter.ShopsPresenter;

@Singleton
@Component(modules = {RepositoryModule.class, UseCaseModule.class, DataMapperModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(ShopsPresenter presenter);

    void inject(ProductListPresenter presenter);
}
