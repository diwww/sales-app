package ru.gcsales.app.data.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.data.AppDatabase;
import ru.gcsales.app.data.ProductDAO;
import ru.gcsales.app.data.ShoppingListDAO;
import ru.gcsales.app.data.model.local.ProductWithShop;
import ru.gcsales.app.data.model.local.ShoppingListEntity;
import ru.gcsales.app.data.model.local.ShoppingListProductEntity;
import ru.gcsales.app.data.model.mapper.ProductMapper;
import ru.gcsales.app.data.model.mapper.ShoppingListMapper;
import ru.gcsales.app.data.model.remote.ProductResponse;
import ru.gcsales.app.data.service.ShoppingListService;
import ru.gcsales.app.domain.model.ProductItem;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public class ShoppingListRepositoryImpl extends TokenRepositoryImpl implements ShoppingListRepository {

    private ShoppingListService mShoppingListService;
    private ShoppingListDAO mShoppingListDAO;
    private ProductDAO mProductDAO;


    private ShoppingListMapper mShoppingListMapper = new ShoppingListMapper();
    private ProductMapper mProductMapper = new ProductMapper();

    public ShoppingListRepositoryImpl(Context context, ShoppingListService shoppingListService,
                                      AppDatabase database) {
        super(context);
        mShoppingListService = shoppingListService;
        mShoppingListDAO = database.getShoppingListDAO();
        mProductDAO = database.getProductDAO();
    }

    @Override
    public Observable<List<ShoppingList>> getShoppingLists() {
        Observable<List<ShoppingListEntity>> remoteObservable = mShoppingListService.getShoppingLists(getAuthHeader())
                .flatMap(response -> {
                    // FIXME: очищаются все шоплисты, и поэтому удаляются связи
                    // (хотя это мб нормально, т.к. все загружается заново)
                    mShoppingListDAO.clearShoppingListTable();
                    mShoppingListDAO.addShoppingLists(mShoppingListMapper.transformResponse(response));
                    return mShoppingListDAO.getShoppingLists().toObservable();
                });

        return remoteObservable
                .onErrorResumeNext(mShoppingListDAO.getShoppingLists().toObservable())
                .map(mShoppingListMapper::transformEntity);
    }


    @Override
    public Observable<ShoppingList> getShoppingList(long id) {
        Observable<List<ProductWithShop>> remoteObservable = mShoppingListService.getShoppingList(getAuthHeader(), id)
                .flatMap(response -> {
                    // FIXME: очищаются все шоплисты, вместо одного
                    // Clears old local data
                    mShoppingListDAO.clearShoppingListProductTable(id);
                    // Insert mProductItems from remote shopping list in db
                    mProductDAO.insert(mProductMapper.transformResponse(response.getItems()));

                    // Register mProductItems within shopping list
                    List<ShoppingListProductEntity> list = new ArrayList<>();
                    for (ProductResponse p : response.getItems()) {
                        list.add(new ShoppingListProductEntity(id, p.getId()));
                    }

                    // Establish many-to-many relation (shopping lists with mProductItems)
                    mShoppingListDAO.addShoppingListProducts(list);
                    return mShoppingListDAO.getShoppingListProducts(id).toObservable();
                });

        return remoteObservable
                .onErrorResumeNext(mShoppingListDAO.getShoppingListProducts(id).toObservable())
                .map(data -> {
                    // TODO: брать имя списка и другую инфу из бд, а не создавать объект
                    ShoppingList shoppingList = new ShoppingList();
                    shoppingList.setId(id);

                    List<ProductItem> items = new ArrayList<>();
                    for (ProductWithShop entity : data) {
                        items.add(mProductMapper.transformEntity(entity));
                    }
                    shoppingList.setItems(items);

                    return shoppingList;
                });
    }

    @Override
    public Observable<ShoppingList> addShoppingList(String name) {
        return mShoppingListService.addShoppingList(getAuthHeader(),
                new ShoppingListService.ShoppingListBody(name))
                .map(response -> {
                    long id = mShoppingListDAO.addShoppingList(mShoppingListMapper.transformResponse(response));
                    return mShoppingListDAO.getShoppingList(id);
                })
                .map(entity -> mShoppingListMapper.transformEntity(entity));
    }

    @Override
    public Observable<String> removeShoppingList(long id) {
        return mShoppingListService.removeShoppingList(getAuthHeader(), id);
    }
}
