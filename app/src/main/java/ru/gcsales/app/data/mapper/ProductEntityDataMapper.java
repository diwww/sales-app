package ru.gcsales.app.data.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.data.entity.ProductEntity;
import ru.gcsales.app.domain.model.Product;

/**
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class ProductEntityDataMapper {

    /**
     * Transforms a single entity.
     *
     * @param productEntity entity to transform
     * @return {@link Product} domain model
     */
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

    /**
     * Transforms a list of entities.
     *
     * @param productEntities list of entities to transform
     * @return a list of {@link Product} domain models
     */
    public List<Product> transform(List<ProductEntity> productEntities) {
        List<Product> products;

        if (productEntities != null && !productEntities.isEmpty()) {
            products = new ArrayList<>();
            for (ProductEntity productEntity : productEntities) {
                products.add(transform(productEntity));
            }
        } else {
            return Collections.emptyList();
        }

        return products;
    }
}
