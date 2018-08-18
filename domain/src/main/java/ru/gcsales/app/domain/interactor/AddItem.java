package ru.gcsales.app.domain.interactor;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * Use case for adding an item to a shopping list.
 *
 * @author Maxim Surovtsev
 * Created on 8/16/18
 */
public class AddItem extends UseCase<String, AddItem.Params> {

    private ShoppingListRepository mShoppingListRepository;

    @Inject
    public AddItem(ShoppingListRepository shoppingListRepository,
                   PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mShoppingListRepository = shoppingListRepository;
    }

    @Override
    Observable<String> buildObservable(Params params) {
        return mShoppingListRepository.addItem(params.shoppingListId, params.itemId);
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
         * @return new params instance.
         */
        public static Params get(long shoppingListId, long itemId) {
            return new Params(shoppingListId, itemId);
        }
    }
}
