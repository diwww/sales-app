package ru.gcsales.app.mapper.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.mapper.DataMapper;
import ru.gcsales.app.presentation.mvp.model.ProductModel;

/**
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class ProductModelDataMapper extends DataMapper<Product, ProductModel> {

    /**
     * Transforms a single model.
     *
     * @param product model to transform
     * @return {@link ProductModel} presentation model
     */
    @Override
    public ProductModel transform(Product product) {
        ProductModel productModel = null;
        if (product != null) {
            productModel = new ProductModel();
            productModel.setId(product.getId());
            productModel.setName(product.getName());
            productModel.setImageUrl(product.getImageUrl());
            productModel.setCategory(product.getCategory());
            productModel.setCondition(product.getCondition());
            productModel.setDateIn(product.getDateIn());
            productModel.setDateOut(product.getDateOut());
            productModel.setDiscount(product.getDiscount());
            productModel.setOldPrice(product.getOldPrice());
            productModel.setNewPrice(product.getNewPrice());
        }
        return productModel;
    }
}
