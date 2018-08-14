package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.data.AppDatabase;
import ru.gcsales.app.data.ProductDAO;
import ru.gcsales.app.data.model.local.ProductWithShop;
import ru.gcsales.app.data.model.mapper.ProductMapper;
import ru.gcsales.app.data.service.ProductService;
import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.domain.repository.ProductRepository;

/**
 * Implementation of {@link ProductRepository},
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
 * Created on 7/29/18
 */
public class ProductRepositoryImpl implements ProductRepository {

    private ProductService mProductService;
    private ProductDAO mProductDAO;
    private ProductMapper mProductMapper = new ProductMapper();

    public ProductRepositoryImpl(ProductService productService, AppDatabase database) {
        mProductService = productService;
        mProductDAO = database.getProductDAO();
    }

    @Override
    public Observable<List<Product>> getProducts(long shopId, String category, int page) {
        Observable<List<ProductWithShop>> remoteObservable = mProductService.getProducts(shopId, category, page)
                .flatMap(response -> {
                    // Write fresh data from network to db
                    // FIXME: таблица очищается при загрузке каждой страницы
                    // (если сделать page == 1 то уже нет, но это решение корявое имхо)
                    if (page == 1) {
                        mProductDAO.clearTable();
                    }
                    mProductDAO.insert(mProductMapper.transformResponse(response.getProductRespons()));
                    // Get written data from db
                    return createDbObservable(shopId, category, page);
                });

        return remoteObservable
                .onErrorResumeNext(createDbObservable(shopId, category, page))
                .map(mProductMapper::transformEntity);
    }

    private Observable<List<ProductWithShop>> createDbObservable(long shopId, String category, int page) {
        Observable<List<ProductWithShop>> observable;

        if (category == null) {
            observable = mProductDAO.getProducts(shopId, page).toObservable();
        } else {
            observable = mProductDAO.getProducts(shopId, category, page).toObservable();
        }

        return observable;
    }
}
