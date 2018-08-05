package ru.gcsales.app.mapper.entity;

import ru.gcsales.app.data.entity.ProductEntity;
import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.mapper.DataMapper;

/**
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class ProductEntityDataMapper extends DataMapper<ProductEntity, Product> {

    /**
     * Transforms a single entity.
     *
     * @param productEntity entity to transform
     * @return {@link Product} domain model
     */
    @Override
    public Product transform(ProductEntity productEntity) {
        Product product = null;
        if (productEntity != null) {
            product = new Product();
            product.setId(productEntity.getId());
            product.setName(productEntity.getName());
            product.setImageUrl(productEntity.getImageUrl());
            product.setCategory(productEntity.getCategory());
            product.setCondition(productEntity.getCondition());
            product.setDateIn(productEntity.getDateIn());
            product.setDateOut(productEntity.getDateOut());
            product.setDiscount(productEntity.getDiscount());
            product.setOldPrice(productEntity.getOldPrice());
            product.setNewPrice(productEntity.getNewPrice());
        }
        return product;
    }
}
