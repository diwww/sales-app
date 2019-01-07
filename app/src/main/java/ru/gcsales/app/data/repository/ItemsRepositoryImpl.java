package ru.gcsales.app.data.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Maybe;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.data.model.mapper.ItemResponseMapper;
import ru.gcsales.app.data.model.remote.ItemResponse;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.repository.ItemsRepository;

/**
 * Implementation of {@link ItemsRepository}
 *
 * @author Maxim Surovtsev
 * @since 06/01/2019
 */
public class ItemsRepositoryImpl implements ItemsRepository {

    private ItemResponseMapper mItemResponseMapper = new ItemResponseMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public Maybe<List<Item>> getItems(long id) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ItemResponse>>() {
        }.getType();

        List<ItemResponse> itemResponses = gson.fromJson(AppApplication.loadJSONFromAsset("items.json"), listType);
        try {
            return Maybe.just(mItemResponseMapper.transform(itemResponses, null));
        } catch (Exception e) {
            return Maybe.error(e);
        }
    }
}
