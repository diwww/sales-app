package ru.gcsales.app.domain.interactor;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public class GetShoppingListPreviews extends UseCase<List<ShoppingList>, Void> {

    private ShoppingListRepository mShoppingListRepository;

    public GetShoppingListPreviews(ShoppingListRepository shoppingListRepository) {
        mShoppingListRepository = shoppingListRepository;
    }

    @Override
    Observable<List<ShoppingList>> buildObservable(Void params) {
        return mShoppingListRepository.getPreviews();
    }
}
