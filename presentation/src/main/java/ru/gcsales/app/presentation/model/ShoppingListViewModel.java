package ru.gcsales.app.presentation.model;

/**
 * @author Maxim Surovtsev
 * Created on 8/19/18
 */
public class ShoppingListViewModel {
    private long mId;
    private String mName;

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

    @Override
    public String toString() {
        return mName;
    }
}
