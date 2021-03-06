package ru.gcsales.app.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.ShoppingListEntry;
import ru.gcsales.app.domain.repository.ItemsRepository;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * Item interactor
 *
 * @author Maxim Surovtsev
 * @since 01/01/2019
 */
public class ItemsInteractor {

    private ItemsRepository mItemsRepository;

    @Inject
    public ItemsInteractor(ItemsRepository itemsRepository) {
        mItemsRepository = itemsRepository;
    }

    /**
     * Downloads items for given shop
     *
     * @param id id of the shop
     * @return {@link Maybe} with list of items
     */
    public Maybe<List<Item>> loadItems(long id) {
        return mItemsRepository.getItems(id);
    }

}
