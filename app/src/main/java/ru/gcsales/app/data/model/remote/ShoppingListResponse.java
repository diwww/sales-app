package ru.gcsales.app.data.model.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public class ShoppingListResponse {
    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("items")
    private List<ProductResponse> mItems = null;
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

    public List<ProductResponse> getItems() {
        return mItems;
    }

    public void setItems(List<ProductResponse> items) {
        this.mItems = items;
    }

    public List<CustomItem> getCustomItems() {
        return mCustomItems;
    }

    public void setCustomItems(List<CustomItem> customItems) {
        this.mCustomItems = customItems;
    }
}
