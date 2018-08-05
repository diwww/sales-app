package ru.gcsales.app.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Maxim Surovtsev
 * Created on 8/5/18
 */
public class ShopInfoEntity {

    @SerializedName("shop")
    @Expose
    private ShopEntity mShopEntity;

    @SerializedName("numItems")
    @Expose
    private int mNumItems;

    @SerializedName("categories")
    @Expose
    private List<String> mCategories;

    public ShopEntity getShopEntity() {
        return mShopEntity;
    }

    public void setShopEntity(ShopEntity shopEntity) {
        mShopEntity = shopEntity;
    }

    public int getNumItems() {
        return mNumItems;
    }

    public void setNumItems(int numItems) {
        mNumItems = numItems;
    }

    public List<String> getCategories() {
        return mCategories;
    }

    public void setCategories(List<String> categories) {
        mCategories = categories;
    }
}
