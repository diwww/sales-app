package ru.gcsales.app.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * Use case for getting the specific shopping list.
 *
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
public class GetShoppingList extends UseCase<ShoppingList, GetShoppingList.Params> {

    private ShoppingListRepository mShoppingListRepository;

    @Inject
    public GetShoppingList(ShoppingListRepository shoppingListRepository,
                           PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mShoppingListRepository = shoppingListRepository;
    }

    @Override
    Observable<ShoppingList> buildObservable(Params params) {
        return mShoppingListRepository.getShoppingList(params.id);
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
