package ru.gcsales.app.data.model.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Shopping list JSON response.
 *
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public class ShoppingListResponse {
    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("items")
    private List<ItemResponse> mItems = null;

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

    public List<ItemResponse> getItems() {
        return mItems;
    }

    public void setItems(List<ItemResponse> items) {
        this.mItems = items;
    }
}
