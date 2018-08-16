package ru.gcsales.app.domain.interactor;

import java.util.List;

import io.reactivex.Single;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * Use case for getting all shopping lists.
 *
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public class GetShoppingLists extends UseCase<List<ShoppingList>, Void> {

    private ShoppingListRepository mShoppingListRepository;

    public GetShoppingLists(ShoppingListRepository shoppingListRepository,
                            PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mShoppingListRepository = shoppingListRepository;
    }

    @Override
    Single<List<ShoppingList>> buildSingle(Void params) {
        return mShoppingListRepository.getShoppingLists();
    }
}
