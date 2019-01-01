package ru.gcsales.app.domain.interactor;

import java.util.List;

import io.reactivex.Maybe;
import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.model.ShoppingListEntry;
import ru.gcsales.app.domain.repository.ShopRepository;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * Item interactor
 *
 * @author Maxim Surovtsev
 * @since 01/01/2019
 */
public class ItemInteractor {

    private ShopRepository mShopRepository;
    private ShoppingListRepository mShoppingListRepository;
    private int mPage;

    public ItemInteractor(ShopRepository shopRepository, ShoppingListRepository shoppingListRepository) {
        mShopRepository = shopRepository;
        mShoppingListRepository = shoppingListRepository;
        mPage = 1;
    }

    /**
     * Gets items for given shop, category and page
     *
     * @param shop     shop
     * @param category category
     * @return {@link Maybe} with list of items
     */
    public Maybe<List<Item>> getItems(Shop shop, Category category) {
        return mShopRepository.getItems(shop, category, mPage++);
    }

    /**
     * Adds an item to the shopping list
     *
     * @param item item to be added
     * @return {@link Maybe} with new shopping list entry
     */
    public Maybe<ShoppingListEntry> addToShoppingList(Item item) {
        return mShoppingListRepository.newEntry(item);
    }
}
