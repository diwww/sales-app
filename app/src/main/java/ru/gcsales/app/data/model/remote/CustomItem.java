package ru.gcsales.app.data.model.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Maxim Surovtsev
 * Created on 8/11/18
 */
class CustomItem {

    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("shoplistId")
    private long mShoppingListId;
    @SerializedName("matchingItems")
    private List<ProductResponse> mMatchingItems = null;

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

    public List<ProductResponse> getMatchingItems() {
        return mMatchingItems;
    }

    public void setMatchingItems(List<ProductResponse> matchingItems) {
        this.mMatchingItems = matchingItems;
    }
}
