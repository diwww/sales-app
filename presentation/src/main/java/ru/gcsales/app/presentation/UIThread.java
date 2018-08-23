package ru.gcsales.app.presentation;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.gcsales.app.domain.executor.PostExecutionThread;

/**
 * Post execution thread implementation to publish UI state.
 *
 * @author Maxim Surovtsev
 * Created on 8/17/18
 */
public class UIThread implements PostExecutionThread {
    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
