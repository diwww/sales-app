package ru.gcsales.app.domain.interactor;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
public class GetShoppingList extends UseCase<ShoppingList, GetShoppingList.Params> {

    private ShoppingListRepository mShoppingListRepository;

    public GetShoppingList(ShoppingListRepository shoppingListRepository) {
        mShoppingListRepository = shoppingListRepository;
    }

    @Override
    Observable<ShoppingList> buildObservable(Params params) {
        return mShoppingListRepository.getShoppingList(params.id);
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
