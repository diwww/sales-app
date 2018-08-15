package ru.gcsales.app.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
public class CustomItem implements Item {

    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("shoplistId")
    private long mShoppingListId;
    @SerializedName("matchingItems")
    private List<ProductItem> mMatchingItems = null;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public long getShoppingListId() {
        return mShoppingListId;
    }

    public void setShoppingListId(long shoppingListId) {
        this.mShoppingListId = shoppingListId;
    }

    public List<ProductItem> getMatchingItems() {
        return mMatchingItems;
    }

    public void setMatchingItems(List<ProductItem> matchingItems) {
        this.mMatchingItems = matchingItems;
    }

    @Override
    public int getType() {
        return CUSTOM_ITEM_TYPE;
    }
}
