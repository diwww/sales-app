package ru.gcsales.app.data.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.model.ShopInfo;

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
     * @return {@link Observable} list of {@link Shop} objects
     */
    @GET("shops")
    Observable<List<Shop>> getShops();

    /**
     * Gets shop info.
     *
     * @param id id of a shop
     * @return {@link Observable} list of {@link ShopInfo} objects
     */
    @GET("shops/{id}/info")
    Observable<ShopInfo> getShopInfo(@Path("id") long id);

}
