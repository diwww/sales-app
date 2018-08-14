package ru.gcsales.app.data.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.data.model.local.ProductEntity;
import ru.gcsales.app.data.model.local.ProductWithShop;
import ru.gcsales.app.data.model.remote.ProductResponse;
import ru.gcsales.app.domain.model.Product;
import ru.gcsales.app.domain.model.Shop;

/**
 * @author Maxim Surovtsev
 * Created on 8/12/18
 */
public class ProductMapper {

    public ProductEntity transformResponse(ProductResponse response) {
        ProductEntity entity = null;

        if (response != null) {
            entity = new ProductEntity();
            entity.setId(response.getId());
            entity.setName(response.getName());
            entity.setCategory(response.getCategory());
            entity.setImageUrl(response.getImageUrl());
            entity.setCondition(response.getCondition());
            entity.setCrawlDate(response.getCrawlDate());
            entity.setDateIn(response.getDateIn());
            entity.setDateOut(response.getDateOut());
            entity.setDiscount(response.getDiscount());
            entity.setOldPrice(response.getOldPrice());
            entity.setNewPrice(response.getNewPrice());
            entity.setShopId(response.getShopResponse().getId());
        }

        return entity;
    }

    public List<ProductEntity> transformResponse(List<ProductResponse> responseList) {
        List<ProductEntity> entityList;

        if (responseList != null && !responseList.isEmpty()) {
            entityList = new ArrayList<>();
            for (ProductResponse response : responseList) {
                entityList.add(transformResponse(response));
            }
        } else {
            return Collections.emptyList();
        }

        return entityList;
    }

    public Product transformEntity(ProductWithShop productWithShop) {
        Product product = null;

        if (productWithShop != null) {
            product = new Product();
            product.setId(productWithShop.getProductEntity().getId());
            product.setName(productWithShop.getProductEntity().getName());
            product.setCategory(productWithShop.getProductEntity().getCategory());
            product.setImageUrl(productWithShop.getProductEntity().getImageUrl());
            product.setCondition(productWithShop.getProductEntity().getCondition());
            product.setCrawlDate(productWithShop.getProductEntity().getCrawlDate());
            product.setDateIn(productWithShop.getProductEntity().getDateIn());
            product.setDateOut(productWithShop.getProductEntity().getDateOut());
            product.setDiscount(productWithShop.getProductEntity().getDiscount());
            product.setOldPrice(productWithShop.getProductEntity().getOldPrice());
            product.setNewPrice(productWithShop.getProductEntity().getNewPrice());

            Shop shop = new Shop();
            shop.setId(productWithShop.getProductEntity().getShopId());
            shop.setName(productWithShop.getShopName());
            shop.setAlias(productWithShop.getShopAlias());
            shop.setImageUrl(productWithShop.getShopImageUrl());

            product.setShop(shop);
        }

        return product;
    }

    public List<Product> transformEntity(List<ProductWithShop> productWithShopList) {
        List<Product> entityList;

        if (productWithShopList != null && !productWithShopList.isEmpty()) {
            entityList = new ArrayList<>();
            for (ProductWithShop productWithShop : productWithShopList) {
                entityList.add(transformEntity(productWithShop));
            }
        } else {
            return Collections.emptyList();
        }

        return entityList;
    }
}
