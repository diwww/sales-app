package ru.gcsales.app.data.model.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Maxim Surovtsev
 * Created on 8/5/18
 */
public class ShopInfo {

    @SerializedName("shop")
    private ShopResponse mShopResponse;

    @SerializedName("categories")
    private List<String> mCategories;

    public ShopResponse getShopResponse() {
        return mShopResponse;
    }

    public void setShopResponse(ShopResponse shopResponse) {
        mShopResponse = shopResponse;
    }

    public List<String> getCategories() {
        return mCategories;
    }

    public void setCategories(List<String> categories) {
        mCategories = categories;
    }
}
