package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public interface CategoryRepository {

    Observable<List<String>> getCategories(long shopId);
}
