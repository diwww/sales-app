package ru.gcsales.app.domain.interactor;

import io.reactivex.Observable;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/16/18
 */
public class DeleteItem extends UseCase<String, DeleteItem.Params> {

    private ShoppingListRepository mShoppingListRepository;

    public DeleteItem(ShoppingListRepository shoppingListRepository) {
        mShoppingListRepository = shoppingListRepository;
    }

    @Override
    Observable<String> buildObservable(Params params) {
        return mShoppingListRepository.deleteItem(params.shoppingListId, params.itemId);
    }

    public static class Params {
        long shoppingListId;
        long itemId;

        public Params(long shoppingListId, long itemId) {
            this.shoppingListId = shoppingListId;
            this.itemId = itemId;
        }

        public static Params forItem(long shoppingListId, long itemId) {
            return new Params(shoppingListId, itemId);
        }
    }
}
