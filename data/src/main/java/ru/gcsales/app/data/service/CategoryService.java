package ru.gcsales.app.data.service;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Retrofit service for retrieving categories.
 *
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public interface CategoryService {

    /**
     * Gets categories for given shop
     *
     * @param shopId shop id
     * @return {@link Single} list of category strings
     */
    @GET("shops/{id}/categories")
    Single<List<String>> getCategories(@Path("id") long shopId);
}
