package ru.gcsales.app.data.repository;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import ru.gcsales.app.data.service.ShoppingListService;
import ru.gcsales.app.domain.model.ShoppingListPreview;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public class ShoppingListRepositoryImpl extends TokenRepositoryImpl implements ShoppingListRepository {

    private ShoppingListService mShoppingListService;

    public ShoppingListRepositoryImpl(ShoppingListService shoppingListService, Context context) {
        super(context);
        mShoppingListService = shoppingListService;
    }

    @Override
    public Observable<List<ShoppingListPreview>> getPreviews() {
        return mShoppingListService.getPreviews(getAuthHeader());
    }

    @Override
    public Observable<ShoppingListPreview> addShoppingList(String name) {
        return mShoppingListService.addShoppingList(getAuthHeader(),
                new ShoppingListService.ShoppingListBody(name));
    }

    @Override
    public Observable<String> removeShoppingList(long id) {
        return mShoppingListService.removeShoppingList(getAuthHeader(), id);
    }
}
