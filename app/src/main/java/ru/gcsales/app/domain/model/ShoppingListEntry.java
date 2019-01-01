package ru.gcsales.app.domain.model;

import java.util.Objects;

/**
 * Shopping list entry
 *
 * @author Maxim Surovtsev
 * @since 01/01/2019
 */
public class ShoppingListEntry {

    private long mId;
    private Item mItem;
    private int mQuantity;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public Item getItem() {
        return mItem;
    }

    public void setItem(Item item) {
        mItem = item;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingListEntry)) {
            return false;
        }
        ShoppingListEntry that = (ShoppingListEntry) o;
        return mId == that.mId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId);
    }

    @Override
    public String toString() {
        return "ShoppingListEntry{" +
                "mId=" + mId +
                ", mItem=" + mItem +
                ", mQuantity=" + mQuantity +
                '}';
    }
}
