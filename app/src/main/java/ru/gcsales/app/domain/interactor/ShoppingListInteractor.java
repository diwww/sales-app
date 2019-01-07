package ru.gcsales.app.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import ru.gcsales.app.domain.model.ShoppingListEntry;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * Shopping list interactor
 *
 * @author Maxim Surovtsev
 * @since 01/01/2019
 */
public class ShoppingListInteractor {

    private ShoppingListRepository mRepository;

    @Inject
    public ShoppingListInteractor(ShoppingListRepository repository) {
        mRepository = repository;
    }

    /**
     * Gets shopping list entries
     *
     * @return {@link Maybe} with shopping list entries
     */
    public Maybe<List<ShoppingListEntry>> loadEntries() {
        return mRepository.getEntries();
    }

    /**
     * Increments the quantity of the entry
     *
     * @param entry shopping list entry
     * @return {@link Maybe} with updated entry
     */
    public Completable incrementQuantity(ShoppingListEntry entry) {
        return mRepository.incrementQuantity(entry);
    }

    /**
     * Decrements the quantity of the entry
     *
     * @param entry shopping list entry
     * @return {@link Maybe} with updated entry
     */
    public Completable decrementQuantity(ShoppingListEntry entry) {
        return mRepository.decrementQuantity(entry);
    }

    /**
     * Removes the entry from the shopping list
     *
     * @param entry shopping list entry
     * @return {@link Completable} with the result of removal
     */
    public Completable removeEntry(ShoppingListEntry entry) {
        return mRepository.removeEntry(entry);
    }
}
