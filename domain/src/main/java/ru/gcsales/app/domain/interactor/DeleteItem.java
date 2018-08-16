package ru.gcsales.app.domain.interactor;

import io.reactivex.Single;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * Use case for deleting an item from a shopping list.
 *
 * @author Maxim Surovtsev
 * Created on 8/16/18
 */
public class DeleteItem extends UseCase<String, DeleteItem.Params> {

    private ShoppingListRepository mShoppingListRepository;

    public DeleteItem(ShoppingListRepository shoppingListRepository,
                      PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mShoppingListRepository = shoppingListRepository;
    }

    @Override
    Single<String> buildSingle(Params params) {
        return mShoppingListRepository.deleteItem(params.shoppingListId, params.itemId);
    }


    /**
     * Class for passing parameters to a use case.
     */
    public static class Params {
        long shoppingListId;
        long itemId;

        public Params(long shoppingListId, long itemId) {
            this.shoppingListId = shoppingListId;
            this.itemId = itemId;
        }

        /**
         * Gets params instance.
         *
         * @param shoppingListId shopping list id
         * @param itemId         item id
         * @return new params instance
         */
        public static Params get(long shoppingListId, long itemId) {
            return new Params(shoppingListId, itemId);
        }
    }
}
