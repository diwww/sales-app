package ru.gcsales.app.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public class ShoppingListPreview {

    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("items")
    private List<String> mItems;
    @SerializedName("customItems")
    private List<String> mCustomItems;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<String> getItems() {
        return mItems;
    }

    public void setItems(List<String> items) {
        mItems = items;
    }

    public List<String> getCustomItems() {
        return mCustomItems;
    }

    public void setCustomItems(List<String> customItems) {
        mCustomItems = customItems;
    }
}
