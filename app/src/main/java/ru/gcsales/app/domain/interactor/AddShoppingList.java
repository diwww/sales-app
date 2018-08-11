package ru.gcsales.app.domain.interactor;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.ShoppingListPreview;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/10/18
 */
public class AddShoppingList extends UseCase<ShoppingListPreview, AddShoppingList.Params> {

    private ShoppingListRepository mShoppingListRepository;

    public AddShoppingList(ShoppingListRepository shoppingListRepository) {
        mShoppingListRepository = shoppingListRepository;
    }

    @Override
    Observable<ShoppingListPreview> buildObservable(Params params) {
        return mShoppingListRepository.addShoppingList(params.mName);
    }

    public static class Params {
        private String mName;

        public Params(String name) {
            mName = name;
        }

        public static Params forShoppingList(String name) {
            return new Params(name);
        }
    }
}
