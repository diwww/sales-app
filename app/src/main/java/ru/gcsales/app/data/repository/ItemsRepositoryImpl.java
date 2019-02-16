package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Maybe;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.data.model.mapper.ItemResponseMapper;
import ru.gcsales.app.data.model.remote.ItemResponse;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.repository.ItemsRepository;
import ru.gcsales.app.presentation.Utils;

/**
 * Implementation of {@link ItemsRepository}
 *
 * @author Maxim Surovtsev
 * @since 06/01/2019
 */
public class ItemsRepositoryImpl implements ItemsRepository {

    private ItemResponseMapper mMapper = new ItemResponseMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public Maybe<List<Item>> getItems(long id) {
        try {
            List<ItemResponse> itemResponses = Utils.parseJsonList(AppApplication
                    .getContext()
                    .getAssets()
                    .open("items.json"), ItemResponse.class);
            return Maybe.just(mMapper.transform(itemResponses, null));
        } catch (Exception e) {
            e.printStackTrace();
            return Maybe.error(e);
        }
    }
}
