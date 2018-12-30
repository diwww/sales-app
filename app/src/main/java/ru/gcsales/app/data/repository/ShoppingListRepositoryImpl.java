package ru.gcsales.app.data.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.gcsales.app.data.AppDatabase;
import ru.gcsales.app.data.dao.ItemDAO;
import ru.gcsales.app.data.dao.ShoppingListDAO;
import ru.gcsales.app.data.model.local.ItemWithShop;
import ru.gcsales.app.data.model.local.ShoppingListEntity;
import ru.gcsales.app.data.model.local.ShoppingListItemEntity;
import ru.gcsales.app.data.model.mapper.ItemEntityMapper;
import ru.gcsales.app.data.model.mapper.ItemResponseMapper;
import ru.gcsales.app.data.model.mapper.ShoppingListEntityMapper;
import ru.gcsales.app.data.model.mapper.ShoppingListResponseMapper;
import ru.gcsales.app.data.model.remote.ItemResponse;
import ru.gcsales.app.data.service.ShoppingListService;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * Implementation of {@link ShoppingListRepository},
 * which gets data from internet and saves to database.
 * <p>
 * There are two scenarios:
 * <ol>
 * <li>If an internet connection is not available, then the data is fetched from a database
 * and returned.</li>
 * <li>If an internet connection is available, then the data is fetched from an internet, then
 * it is written to a database and finally it is fetched from a database and returned.</li>
 * </ol>
 * </p>
 *
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public class ShoppingListRepositoryImpl extends TokenRepositoryImpl implements ShoppingListRepository {

    private ShoppingListService mShoppingListService;
    private ShoppingListDAO mShoppingListDAO;
    private ItemDAO mItemDAO;

    private ShoppingListResponseMapper mShoppingListResponseMapper = new ShoppingListResponseMapper();
    private ShoppingListEntityMapper mShoppingListEntityMapper = new ShoppingListEntityMapper();
    private ItemResponseMapper mItemResponseMapper = new ItemResponseMapper();
    private ItemEntityMapper mItemEntityMapper = new ItemEntityMapper();

    public ShoppingListRepositoryImpl(Context context, ShoppingListService shoppingListService,
                                      AppDatabase database) {
        super(context);
        mShoppingListService = shoppingListService;
        mShoppingListDAO = database.getShoppingListDAO();
        mItemDAO = database.getItemDAO();
    }

    @Override
    public Observable<List<ShoppingList>> getShoppingLists() {
        // 1. Network scenario
        Single<List<ShoppingListEntity>> remote = mShoppingListService.getShoppingLists(getAuthHeader())
                .flatMap(responseList -> {
                    // Clear old local data
                    mShoppingListDAO.deleteAllShoppingLists();
                    // Insert new data from remote source
                    mShoppingListDAO.insertShoppingLists(mShoppingListResponseMapper.transform(responseList, null));
                    return mShoppingListDAO.getShoppingLists();
                });

        // 2. Db scenario
        Single<List<ShoppingListEntity>> local = mShoppingListDAO.getShoppingLists();

        return Observable.concatArray(local.toObservable(), remote.toObservable())
                .map(data -> mShoppingListEntityMapper.transform(data, null));
    }

    @Override
    public Observable<ShoppingList> getShoppingList(long id) {
        // 1. Network scenario
        Single<List<ItemWithShop>> remote = mShoppingListService.getShoppingList(getAuthHeader(), id)
                .flatMap(response -> {
                    // Clear old local data
                    mShoppingListDAO.clearShoppingListItemTable(id);
                    // Insert items from remote shopping list into item table
                    // to satisfy foreign key constraints
                    mItemDAO.insert(mItemResponseMapper.transform(response.getItems(), null));
                    // Associate items with shopping list
                    List<ShoppingListItemEntity> list = new ArrayList<>();
                    for (ItemResponse i : response.getItems()) {
                        list.add(new ShoppingListItemEntity(id, i.getId()));
                    }
                    // Add associations to db
                    mShoppingListDAO.insertItems(list);
                    return mShoppingListDAO.getItems(id);
                });

        // 2. Db scenario
        Single<List<ItemWithShop>> local = mShoppingListDAO.getItems(id);

        return Observable.concatArray(local.toObservable(), remote.toObservable())
                .map(itemsWithShops -> {
                    ShoppingList shoppingList = mShoppingListEntityMapper
                            .transform(mShoppingListDAO.getShoppingList(id), null);

                    List<Item> items = new ArrayList<>();
                    for (ItemWithShop entity : itemsWithShops) {
                        items.add(mItemEntityMapper.transform(entity, null));
                    }

                    shoppingList.setItems(items);

                    return shoppingList;
                });
    }

    @Override
    public Observable<ShoppingList> addShoppingList(String name) {
        ShoppingListService.ShoppingListBody body = new ShoppingListService.ShoppingListBody(name);
        return mShoppingListService.addShoppingList(getAuthHeader(), body)
                .map(res -> {
                    long id = mShoppingListDAO
                            .insertShoppingList(mShoppingListResponseMapper.transform(res, null));
                    return mShoppingListEntityMapper
                            .transform(mShoppingListDAO.getShoppingList(id), null);
                })
                .toObservable();
    }

    @Override
    public Observable<String> deleteShoppingList(long id) {
        return mShoppingListService.removeShoppingList(getAuthHeader(), id)
                .doOnSuccess(res -> mShoppingListDAO.deleteShoppingList(id))
                .toObservable();
    }

    @Override
    public Observable<String> addItem(long shoppingListId, long itemId) {
        return mShoppingListService.addItem(getAuthHeader(), shoppingListId, itemId)
                .doOnSuccess(res -> {
                    ShoppingListItemEntity entity = new ShoppingListItemEntity(shoppingListId, itemId);
                    mShoppingListDAO.insertItem(entity);
                })
                .toObservable();
    }

    @Override
    public Observable<String> deleteItem(long shoppingListId, long itemId) {
        return mShoppingListService.deleteItem(getAuthHeader(), shoppingListId, itemId)
                .doOnSuccess(res -> mShoppingListDAO.deleteShoppingListItem(shoppingListId, itemId))
                .toObservable();
    }
}
