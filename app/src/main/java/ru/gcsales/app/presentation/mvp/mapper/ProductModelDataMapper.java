package ru.gcsales.app.presentation.mvp.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.presentation.mvp.model.ProductModel;

/**
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class ProductModelDataMapper {

    /**
     * Transforms a single model.
     *
     * @param product model to transform
     * @return {@link ProductModel} presentation model
     */
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

    /**
     * Transforms a list of models.
     *
     * @param products list of models to transform
     * @return a list of {@link ProductModel} presentation models
     */
    public List<ProductModel> transform(List<Product> products) {
        List<ProductModel> productModels;

        if (products != null && !products.isEmpty()) {
            productModels = new ArrayList<>();
            for (Product product : products) {
                productModels.add(transform(product));
            }
        } else {
            return Collections.emptyList();
        }

        return productModels;
    }
}
