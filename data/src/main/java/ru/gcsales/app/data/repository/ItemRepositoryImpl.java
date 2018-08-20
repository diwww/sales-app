package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.gcsales.app.data.AppDatabase;
import ru.gcsales.app.data.dao.ItemDAO;
import ru.gcsales.app.data.model.local.ItemWithShop;
import ru.gcsales.app.data.model.mapper.ItemMapper;
import ru.gcsales.app.data.service.ItemService;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.repository.ItemRepository;

/**
 * Implementation of {@link ItemRepository},
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
 * Created on 8/16/18
 */
public class ItemRepositoryImpl implements ItemRepository {

    private ItemService mItemService;
    private ItemDAO mItemDAO;
    private ItemMapper mItemMapper = new ItemMapper();

    public ItemRepositoryImpl(ItemService itemService, AppDatabase appDatabase) {
        mItemService = itemService;
        mItemDAO = appDatabase.getItemDAO();
    }

    @Override
    public Observable<List<Item>> getItems(long shopId, String category, int page) {
        Single<List<ItemWithShop>> remote = mItemService.getItems(shopId, category, page)
                .flatMap(response -> {
                    // Write fresh data from network to db
                    // FIXME: таблица очищается при загрузке каждой страницы
                    // (если сделать page == 1 то уже нет, но это решение корявое имхо)
                    if (page == 1) {
                        mItemDAO.clearTable();
                    }
                    mItemDAO.insert(mItemMapper.transformResponse(response.getItemResponses()));
                    // Get written data from db
                    return createDbSingle(shopId, category, page);
                });

        return remote
                .onErrorResumeNext(createDbSingle(shopId, category, page))
                .map(mItemMapper::transformEntity)
                .toObservable();
    }

    private Single<List<ItemWithShop>> createDbSingle(long shopId, String category, int page) {
        Single<List<ItemWithShop>> single;

        if (category == null) {
            single = mItemDAO.getItems(shopId, page);
        } else {
            single = mItemDAO.getItems(shopId, category, page);
        }

        return single;
    }
}
