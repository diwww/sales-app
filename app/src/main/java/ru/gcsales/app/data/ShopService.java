package ru.gcsales.app.data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.gcsales.app.data.entity.ShopEntity;
import ru.gcsales.app.data.entity.ShopInfoEntity;

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

    /**
     * Gets shop info.
     *
     * @param id id of a shop
     * @return {@link Observable} list of {@link ShopInfoEntity} objects
     */
    @GET("shops/{id}/info")
    Observable<ShopInfoEntity> getShopInfo(@Path("id") long id);

}
