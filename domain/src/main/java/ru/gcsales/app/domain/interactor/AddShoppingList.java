package ru.gcsales.app.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.gcsales.app.domain.executor.PostExecutionThread;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * Use case for adding a new shopping list.
 *
 * @author Maxim Surovtsev
 * Created on 8/10/18
 */
public class AddShoppingList extends UseCase<ShoppingList, AddShoppingList.Params> {

    private ShoppingListRepository mShoppingListRepository;

    @Inject
    public AddShoppingList(ShoppingListRepository shoppingListRepository,
                           PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        mShoppingListRepository = shoppingListRepository;
    }

    @Override
    Observable<ShoppingList> buildObservable(Params params) {
        return mShoppingListRepository.addShoppingList(params.mName);
    }

    /**
     * Class for passing parameters to a use case.
     */
    public static class Params {
        private String mName;

        public Params(String name) {
            mName = name;
        }

        /**
         * Gets params instance.
         *
         * @param name shopping list name
         * @return new params instance.
         */
        public static Params get(String name) {
            return new Params(name);
        }
    }
}
