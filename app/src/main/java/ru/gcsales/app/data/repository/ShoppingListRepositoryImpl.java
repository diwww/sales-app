package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import ru.gcsales.app.AppApplication;
import ru.gcsales.app.data.model.mapper.ShoppingListEntryResponseMapper;
import ru.gcsales.app.data.model.remote.ShoppingListEntryResponse;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.ShoppingListEntry;
import ru.gcsales.app.domain.repository.ShoppingListRepository;
import ru.gcsales.app.presentation.Utils;

/**
 * Implementation of {@link ShoppingListRepository}
 *
 * @author Maxim Surovtsev
 * @since 07/01/2019
 */
public class ShoppingListRepositoryImpl implements ShoppingListRepository {

    private ShoppingListEntryResponseMapper mMapper = new ShoppingListEntryResponseMapper();

    @Override
    public Maybe<List<ShoppingListEntry>> getEntries() {
        try {
            List<ShoppingListEntryResponse> entryResponses = Utils.parseJsonList(AppApplication
                    .getContext()
                    .getAssets()
                    .open("shopping_list.json"), ShoppingListEntryResponse.class);
            return Maybe.just(mMapper.transform(entryResponses, null));
        } catch (Exception e) {
            e.printStackTrace();
            return Maybe.error(e);
        }
    }

    @Override
    public Maybe<ShoppingListEntry> newEntry(Item item) {
        return null;
    }

    @Override
    public Completable incrementQuantity(ShoppingListEntry entry) {
        return null;
    }

    @Override
    public Completable decrementQuantity(ShoppingListEntry entry) {
        return null;
    }

    @Override
    public Completable removeEntry(ShoppingListEntry entry) {
        return null;
    }

    @Override
    public Completable setToken(String token) {
        return null;
    }

    @Override
    public Single<String> getToken() {
        return null;
    }

    @Override
    public Completable removeToken() {
        return null;
    }

    @Override
    public Single<String> getAuthHeader() {
        return null;
    }
}
