package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.ShoppingListPreview;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListRepository extends TokenRepository {

    Observable<List<ShoppingListPreview>> getPreviews();

    Observable<ShoppingListPreview> addShoppingList(String name);
}
