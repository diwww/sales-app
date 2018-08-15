package ru.gcsales.app.data.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.gcsales.app.data.model.remote.ProductsInfoResponse;

/**
 * API requests to get mProductItems.
 *
 * @author Maxim Surovtsev
 * Created on 7/30/18
 */
public interface ProductService {

    /**
     * Gets mProductItems for a given shop.
     *
     * @param id       id of a shop
     * @param page     page number in pagination
     * @param category category to filter mProductItems (optional)
     * @return {@link Observable} of {@link ProductsInfoResponse} object
     */
    @GET("shops/{id}")
    Observable<ProductsInfoResponse> getProducts(@Path("id") long id, @Query("category") String category, @Query("page") int page);
}
