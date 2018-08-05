package ru.gcsales.app.domain.model;

import java.util.List;

/**
 * Domain model to represent shop info.
 *
 * @author Maxim Surovtsev
 * Created on 8/5/18
 */
public class ShopInfo {

    private Shop mShop;
    private List<String> mCategories;
    private int mNumItems;

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
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
