package ru.gcsales.app.data.service;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import ru.gcsales.app.data.model.remote.ShopResponse;

/**
 * Retrofit service for getting shops.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public interface ShopService {

    /**
     * Gets all shops.
     *
     * @return {@link Single} list of {@link ShopResponse} objects
     */
    @GET("shops")
    Single<List<ShopResponse>> getShops();
}
