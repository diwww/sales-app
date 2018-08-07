package ru.gcsales.app.data.repository;

import io.reactivex.Observable;
import ru.gcsales.app.data.service.ProductService;
import ru.gcsales.app.domain.model.ProductsInfo;
import ru.gcsales.app.domain.repository.ProductRepository;
import ru.gcsales.app.mapper.entity.ProductsInfoEntityDataMapper;

/**
 * @author Maxim Surovtsev
 * Created on 7/29/18
 */
public class ProductRepositoryImpl implements ProductRepository {

    private ProductService mProductService;
    private ProductsInfoEntityDataMapper mMapper;

    public ProductRepositoryImpl(ProductService productService, ProductsInfoEntityDataMapper mapper) {
        mProductService = productService;
        mMapper = mapper;
    }

    @Override
    public Observable<ProductsInfo> getProducts(long shopId, String category, int page) {
        return mProductService.getProducts(shopId, category, page)
                .map(data -> mMapper.transform(data));
    }
}
