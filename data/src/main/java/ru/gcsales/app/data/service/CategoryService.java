package ru.gcsales.app.data.service;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public interface CategoryService {

    @GET("shops/{id}/categories")
    Single<List<String>> getCategories(@Path("id") long shopId);
}
