package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.ShoppingList;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListRepository extends TokenRepository {

    Observable<List<ShoppingList>> getPreviews();

    Observable<ShoppingList> addShoppingList(String name);

    Observable<String> removeShoppingList(long id);
}
