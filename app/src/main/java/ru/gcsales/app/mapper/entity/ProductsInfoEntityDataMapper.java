package ru.gcsales.app.mapper.entity;

import ru.gcsales.app.data.entity.ProductsInfoEntity;
import ru.gcsales.app.domain.model.ProductsInfo;
import ru.gcsales.app.mapper.DataMapper;

/**
 * @author Maxim Surovtsev
 * Created on 8/6/18
 */
public class ProductsInfoEntityDataMapper extends DataMapper<ProductsInfoEntity, ProductsInfo> {

    private ProductEntityDataMapper mProductEntityDataMapper;

    public ProductsInfoEntityDataMapper(ProductEntityDataMapper productEntityDataMapper) {
        mProductEntityDataMapper = productEntityDataMapper;
    }

    @Override
    public ProductsInfo transform(ProductsInfoEntity input) {
        ProductsInfo productsInfo = null;
        if (input != null) {
            productsInfo = new ProductsInfo();
            productsInfo.setCount(input.getCount());
            productsInfo.setNumPages(input.getNumPages());
            productsInfo.setProducts(mProductEntityDataMapper.transform(input.getProductEntities()));
        }
        return productsInfo;
    }
}
