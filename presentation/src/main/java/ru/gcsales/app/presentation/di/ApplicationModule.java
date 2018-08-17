package ru.gcsales.app.presentation.di;

import android.app.Application;
import android.content.Context;

import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.presentation.UIThread;

/**
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }
}
