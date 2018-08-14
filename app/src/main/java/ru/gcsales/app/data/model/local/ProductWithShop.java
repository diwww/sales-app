package ru.gcsales.app.data.model.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;

/**
 * @author Maxim Surovtsev
 * Created on 8/13/18
 */
public class ProductWithShop {

    @Embedded
    private ProductEntity mProductEntity;

    @ColumnInfo(name = "shop_name")
    private String mShopName;
    @ColumnInfo(name = "shop_alias")
    private String mShopAlias;
    @ColumnInfo(name = "shop_image_url")
    private String mShopImageUrl;

    public ProductEntity getProductEntity() {
        return mProductEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        mProductEntity = productEntity;
    }

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        mShopName = shopName;
    }

    public String getShopAlias() {
        return mShopAlias;
    }

    public void setShopAlias(String shopAlias) {
        mShopAlias = shopAlias;
    }

    public String getShopImageUrl() {
        return mShopImageUrl;
    }

    public void setShopImageUrl(String shopImageUrl) {
        mShopImageUrl = shopImageUrl;
    }
}
