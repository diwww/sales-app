package ru.gcsales.app.data.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import ru.gcsales.app.data.AppDatabase;
import ru.gcsales.app.data.ItemDAO;
import ru.gcsales.app.data.ShoppingListDAO;
import ru.gcsales.app.data.model.local.ItemWithShop;
import ru.gcsales.app.data.model.local.ShoppingListEntity;
import ru.gcsales.app.data.model.local.ShoppingListProductEntity;
import ru.gcsales.app.data.model.mapper.ItemMapper;
import ru.gcsales.app.data.model.mapper.ShoppingListMapper;
import ru.gcsales.app.data.model.remote.ItemResponse;
import ru.gcsales.app.data.service.ShoppingListService;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public class ShoppingListRepositoryImpl extends TokenRepositoryImpl implements ShoppingListRepository {

    private ShoppingListService mShoppingListService;
    private ShoppingListDAO mShoppingListDAO;
    private ItemDAO mItemDAO;


    private ShoppingListMapper mShoppingListMapper = new ShoppingListMapper();
    private ItemMapper mItemMapper = new ItemMapper();

    public ShoppingListRepositoryImpl(Context context, ShoppingListService shoppingListService,
                                      AppDatabase database) {
        super(context);
        mShoppingListService = shoppingListService;
        mShoppingListDAO = database.getShoppingListDAO();
        mItemDAO = database.getItemDAO();
    }

    @Override
    public Single<List<ShoppingList>> getShoppingLists() {
        Single<List<ShoppingListEntity>> remote = mShoppingListService.getShoppingLists(getAuthHeader())
                .flatMap(response -> {
                    // FIXME: очищаются все шоплисты, и поэтому удаляются связи
                    // (хотя это мб нормально, т.к. все загружается заново)
                    mShoppingListDAO.clearShoppingListTable();
                    mShoppingListDAO.addShoppingLists(mShoppingListMapper.transformResponse(response));
                    return mShoppingListDAO.getShoppingLists();
                });

        return remote
                .onErrorResumeNext(mShoppingListDAO.getShoppingLists())
                .map(mShoppingListMapper::transformEntity);
    }


    @Override
    public Single<ShoppingList> getShoppingList(long id) {
        Single<List<ItemWithShop>> remote = mShoppingListService.getShoppingList(getAuthHeader(), id)
                .flatMap(response -> {
                    // FIXME: очищаются все шоплисты, вместо одного
                    // Clears old local data
                    mShoppingListDAO.clearShoppingListProductTable(id);
                    // Insert mProductItems from remote shopping list in db
                    mItemDAO.insert(mItemMapper.transformResponse(response.getItems()));

                    // Register mProductItems within shopping list
                    List<ShoppingListProductEntity> list = new ArrayList<>();
                    for (ItemResponse p : response.getItems()) {
                        list.add(new ShoppingListProductEntity(id, p.getId()));
                    }

                    // Establish many-to-many relation (shopping lists with mProductItems)
                    mShoppingListDAO.addShoppingListProducts(list);
                    return mShoppingListDAO.getShoppingListProducts(id);
                });

        return remote
                .onErrorResumeNext(mShoppingListDAO.getShoppingListProducts(id))
                .map(data -> {
                    // TODO: брать имя списка и другую инфу из бд, а не создавать объект
                    ShoppingList shoppingList = new ShoppingList();
                    shoppingList.setId(id);

                    List<Item> items = new ArrayList<>();
                    for (ItemWithShop entity : data) {
                        items.add(mItemMapper.transformEntity(entity));
                    }
                    shoppingList.setItems(items);

                    return shoppingList;
                });
    }

    @Override
    public Single<ShoppingList> addShoppingList(String name) {
        return mShoppingListService.addShoppingList(getAuthHeader(),
                new ShoppingListService.ShoppingListBody(name))
                .map(response -> {
                    long id = mShoppingListDAO.addShoppingList(mShoppingListMapper.transformResponse(response));
                    return mShoppingListDAO.getShoppingList(id);
                })
                .map(mShoppingListMapper::transformEntity);
    }

    @Override
    public Single<String> deleteShoppingList(long id) {
        return mShoppingListService.removeShoppingList(getAuthHeader(), id);
    }

    @Override
    public Single<String> addItem(long shoppingListId, long itemId) {
        return mShoppingListService.addItem(getAuthHeader(), shoppingListId, itemId);
    }

    @Override
    public Single<String> deleteItem(long shoppingListId, long itemId) {
        return mShoppingListService.deleteItem(getAuthHeader(), shoppingListId, itemId);
    }
}
