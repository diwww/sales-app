package ru.gcsales.app.data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.gcsales.app.data.entity.ProductEntity;
import ru.gcsales.app.data.entity.ProductsResponse;
import ru.gcsales.app.data.entity.ShopEntity;

/**
 * API requests to get shops.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public interface ShopService {

    /**
     * Gets all shops.
     *
     * @return {@link Observable} list of {@link ShopEntity} objects
     */
    @GET("shops")
    Observable<List<ShopEntity>> getShops();

}
