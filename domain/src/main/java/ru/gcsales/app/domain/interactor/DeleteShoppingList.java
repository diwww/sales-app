package ru.gcsales.app.domain.interactor;

import io.reactivex.Single;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * Use case for deleting a shopping list.
 *
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
public class DeleteShoppingList extends UseCase<String, DeleteShoppingList.Params> {

    private ShoppingListRepository mShoppingListRepository;

    public DeleteShoppingList(ShoppingListRepository shoppingListRepository,
                              PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mShoppingListRepository = shoppingListRepository;
    }

    @Override
    Single<String> buildSingle(Params params) {
        return mShoppingListRepository.deleteShoppingList(params.id);
    }

    /**
     * Class for passing parameters to a use case.
     */
    public static class Params {
        private long id;

        public Params(long id) {
            this.id = id;
        }

        /**
         * Gets params instance.
         *
         * @param id shopping list id
         * @return new params instance
         */
        public static Params forShoppingList(long id) {
            return new Params(id);
        }
    }
}
