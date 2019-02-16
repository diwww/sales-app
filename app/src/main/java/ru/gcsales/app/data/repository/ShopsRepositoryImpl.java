package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Maybe;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.data.model.mapper.ShopResponseMapper;
import ru.gcsales.app.data.model.remote.ShopResponse;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.repository.ShopsRepository;
import ru.gcsales.app.presentation.Utils;

/**
 * Implementation of {@link ShopsRepository}
 *
 * @author Maxim Surovtsev
 * @since 05/01/2019
 */
public class ShopsRepositoryImpl implements ShopsRepository {

    private ShopResponseMapper mMapper = new ShopResponseMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public Maybe<List<Shop>> getShops() {
        try {
            List<ShopResponse> entryResponses = Utils.parseJsonList(AppApplication
                    .getContext()
                    .getAssets()
                    .open("shopping_list.json"), ShopResponse.class);
            return Maybe.just(mMapper.transform(entryResponses, null));
        } catch (Exception e) {
            e.printStackTrace();
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
