package ru.gcsales.app.domain.interactor;

import io.reactivex.Observable;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
public class RemoveShoppingList extends UseCase<String, RemoveShoppingList.Params> {

    private ShoppingListRepository mShoppingListRepository;

    public RemoveShoppingList(ShoppingListRepository shoppingListRepository) {
        mShoppingListRepository = shoppingListRepository;
    }

    @Override
    Observable<String> buildObservable(Params params) {
        return mShoppingListRepository.removeShoppingList(params.id);
    }

    public static class Params {
        private long id;

        public Params(long id) {
            this.id = id;
        }

        public static Params forShoppingList(long id) {
            return new Params(id);
        }
    }
}
