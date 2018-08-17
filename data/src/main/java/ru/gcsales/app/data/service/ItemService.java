package ru.gcsales.app.data.service;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.gcsales.app.data.model.remote.ItemsInfoResponse;

/**
 * API requests to get items.
 *
 * @author Maxim Surovtsev
 * Created on 8/16/18
 */
public interface ItemService {

    /**
     * Gets items for a given shop.
     *
     * @param id       id of a shop
     * @param page     page number in pagination
     * @param category category to filter mProductItems (optional)
     * @return {@link Single} of {@link ItemsInfoResponse} object
     */
    @GET("shops/{id}")
    Single<ItemsInfoResponse> getItems(@Path("id") long id, @Query("category") String category,
                                       @Query("page") int page);

}
