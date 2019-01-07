package ru.gcsales.app.data.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

/**
 * Implementation of {@link ShoppingListRepository}
 *
 * @author Maxim Surovtsev
 * @since 07/01/2019
 */
public class ShoppingListRepositoryImpl implements ShoppingListRepository {

    private ShoppingListEntryResponseMapper mShoppingListEntryResponseMapper = new ShoppingListEntryResponseMapper();

    @Override
    public Maybe<List<ShoppingListEntry>> getEntries() {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ShoppingListEntryResponse>>() { }.getType();

        List<ShoppingListEntryResponse> shoppingListEntryResponses = gson.fromJson(AppApplication.loadJSONFromAsset("shoppinglist.json"), listType);
        try {
            return Maybe.just(mShoppingListEntryResponseMapper.transform(shoppingListEntryResponses, null));
        } catch (Exception e) {
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
