package ru.gcsales.app.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.gcsales.app.data.entity.ProductsResponse;

/**
 * API requests to get products.
 *
 * @author Maxim Surovtsev
 * Created on 7/30/18
 */
public interface ProductService {

    /**
     * Gets products for a given shop.
     *
     * @param id       id of a shop
     * @param page     page number in pagination
     * @param category category to filter products (optional)
     * @return {@link Observable} of {@link ProductsResponse} object
     */
    @GET("shops/{id}")
    Observable<ProductsResponse> getProducts(@Path("id") long id, @Query("category") String category, @Query("page") int page);
}
