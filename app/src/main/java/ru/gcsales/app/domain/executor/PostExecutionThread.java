package ru.gcsales.app.domain.executor;

import io.reactivex.Scheduler;

/**
 * Thread for publishing results to UI.
 *
 * @author Maxim Surovtsev
 * Created on 8/16/18
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
