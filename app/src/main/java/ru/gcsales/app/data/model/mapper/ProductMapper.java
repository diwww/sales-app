package ru.gcsales.app.data.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.gcsales.app.data.model.local.ProductEntity;
import ru.gcsales.app.data.model.local.ProductWithShop;
import ru.gcsales.app.data.model.remote.ProductResponse;
import ru.gcsales.app.domain.model.ProductItem;
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

    public ProductItem transformEntity(ProductWithShop productWithShop) {
        ProductItem productItem = null;

        if (productWithShop != null) {
            productItem = new ProductItem();
            productItem.setId(productWithShop.getProductEntity().getId());
            productItem.setName(productWithShop.getProductEntity().getName());
            productItem.setCategory(productWithShop.getProductEntity().getCategory());
            productItem.setImageUrl(productWithShop.getProductEntity().getImageUrl());
            productItem.setCondition(productWithShop.getProductEntity().getCondition());
            productItem.setCrawlDate(productWithShop.getProductEntity().getCrawlDate());
            productItem.setDateIn(productWithShop.getProductEntity().getDateIn());
            productItem.setDateOut(productWithShop.getProductEntity().getDateOut());
            productItem.setDiscount(productWithShop.getProductEntity().getDiscount());
            productItem.setOldPrice(productWithShop.getProductEntity().getOldPrice());
            productItem.setNewPrice(productWithShop.getProductEntity().getNewPrice());

            Shop shop = new Shop();
            shop.setId(productWithShop.getProductEntity().getShopId());
            shop.setName(productWithShop.getShopName());
            shop.setAlias(productWithShop.getShopAlias());
            shop.setImageUrl(productWithShop.getShopImageUrl());

            productItem.setShop(shop);
        }

        return productItem;
    }

    public List<ProductItem> transformEntity(List<ProductWithShop> productWithShopList) {
        List<ProductItem> entityList;

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
