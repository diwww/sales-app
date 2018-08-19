package ru.gcsales.app.presentation.model;

import java.util.List;

/**
 * @author Maxim Surovtsev
 * Created on 8/19/18
 */
public class ShoppingListViewModel {
    private long mId;
    private String mName;
    private List<ItemViewModel> mItems;

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

    public List<ItemViewModel> getItems() {
        return mItems;
    }

    public void setItems(List<ItemViewModel> items) {
        mItems = items;
    }

    @Override
    public String toString() {
        return mName;
    }
}
