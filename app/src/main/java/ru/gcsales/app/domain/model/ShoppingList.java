package ru.gcsales.app.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public class ShoppingList {
    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("items")
    private List<ProductItem> mItems = null;
    @SerializedName("customItems")
    private List<CustomItem> mCustomItems = null;

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

    public List<ProductItem> getItems() {
        return mItems;
    }

    public void setItems(List<ProductItem> items) {
        this.mItems = items;
    }

    public List<CustomItem> getCustomItems() {
        return mCustomItems;
    }

    public void setCustomItems(List<CustomItem> customItems) {
        this.mCustomItems = customItems;
    }
}
