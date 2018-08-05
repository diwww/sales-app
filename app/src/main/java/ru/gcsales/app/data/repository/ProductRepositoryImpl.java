package ru.gcsales.app.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.data.ProductService;
import ru.gcsales.app.mapper.entity.ProductEntityDataMapper;
import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.domain.repository.ProductRepository;

/**
 * @author Maxim Surovtsev
 * Created on 7/29/18
 */
public class ProductRepositoryImpl implements ProductRepository {

    private ProductService mProductService;
    private ProductEntityDataMapper mProductEntityDataMapper;

    public ProductRepositoryImpl(ProductService productService, ProductEntityDataMapper productEntityDataMapper) {
        mProductService = productService;
        mProductEntityDataMapper = productEntityDataMapper;
    }

    @Override
    public Observable<List<Product>> getProducts(long shopId, String category, int page) {
        return mProductService.getProducts(shopId, category, page)
                .map(data -> mProductEntityDataMapper.transform(data.getProductEntities()));
    }
}
