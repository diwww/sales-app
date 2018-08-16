package ru.gcsales.app.domain.model;


import java.util.List;

/**
 * Shopping list domain model.
 *
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public class ShoppingList {
    private long mId;
    private String mName;
    private List<Item> mItems = null;

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

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        this.mItems = items;
    }
}
