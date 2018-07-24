package ru.gcsales.app.presentation.mvp.model;

import java.util.List;

public class ShoppingListPreviewModel {

    private int mId;
    private String mName;
    private List<String> mItems;
    private List<String> mCustomItems;

    public ShoppingListPreviewModel(int id, String name, List<String> items, List<String> customItems) {
        mId = id;
        mName = name;
        mItems = items;
        mCustomItems = customItems;
    }

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
