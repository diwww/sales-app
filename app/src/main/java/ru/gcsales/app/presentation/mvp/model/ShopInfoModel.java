package ru.gcsales.app.presentation.mvp.model;

import java.util.List;

/**
 * @author Maxim Surovtsev
 * Created on 8/5/18
 */
public class ShopInfoModel {

    private ShopModel mShop;
    private List<String> mCategories;
    private int mNumItems;

    public ShopModel getShop() {
        return mShop;
    }

    public void setShop(ShopModel shop) {
        mShop = shop;
    }

    public List<String> getCategories() {
        return mCategories;
    }

    public void setCategories(List<String> categories) {
        mCategories = categories;
    }

    public int getNumItems() {
        return mNumItems;
    }

    public void setNumItems(int numItems) {
        mNumItems = numItems;
    }
}
