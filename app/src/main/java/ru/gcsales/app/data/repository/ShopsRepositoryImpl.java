package ru.gcsales.app.data.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Maybe;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.data.model.mapper.ShopResponseMapper;
import ru.gcsales.app.data.model.remote.ShopResponse;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.repository.ShopsRepository;

/**
 * Implementation of {@link ShopsRepository}
 *
 * @author Maxim Surovtsev
 * @since 05/01/2019
 */
public class ShopsRepositoryImpl implements ShopsRepository {

    private ShopResponseMapper mShopResponseMapper = new ShopResponseMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public Maybe<List<Shop>> getShops() {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ShopResponse>>() {
        }.getType();

        List<ShopResponse> shopResponses = gson.fromJson(AppApplication.loadJSONFromAsset("shops.json"), listType);
        try {
            return Maybe.just(mShopResponseMapper.transform(shopResponses, null));
        } catch (Exception e) {
            return Maybe.error(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Maybe<Shop> getShop(long id) {
        return null;
    }
}
