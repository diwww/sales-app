package ru.gcsales.app.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Shopping list entry
 *
 * @author Maxim Surovtsev
 * @since 05/01/2019
 */
public class ShoppingListEntry implements Serializable {

    private String mShop;
    private long mItemId;
    private String mTitle;
    private double mPrice;
    private String mImageUrl;
    private int mQuantity;
    private Date mCreated;
    private boolean mShowShop;

    public String getShop() {
        return mShop;
    }

    public ShoppingListEntry setShop(String shop) {
        mShop = shop;
        return this;
    }

    public long getItemId() {
        return mItemId;
    }

    public ShoppingListEntry setItemId(long itemId) {
        mItemId = itemId;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public ShoppingListEntry setTitle(String title) {
        mTitle = title;
        return this;
    }

    public double getPrice() {
        return mPrice;
    }

    public ShoppingListEntry setPrice(double price) {
        mPrice = price;
        return this;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public ShoppingListEntry setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
        return this;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public ShoppingListEntry setQuantity(int quantity) {
        mQuantity = quantity;
        return this;
    }

    public Date getCreated() {
        return mCreated;
    }

    public ShoppingListEntry setCreated(Date created) {
        mCreated = created;
        return this;
    }

    public boolean isShowShop() {
        return mShowShop;
    }

    public ShoppingListEntry setShowShop(boolean showShop) {
        mShowShop = showShop;
        return this;
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
        return mItemId == that.mItemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mItemId);
    }

    @Override
    public String toString() {
        return "ShoppingListEntry{" +
                "mShop='" + mShop + '\'' +
                ", mItemId=" + mItemId +
                ", mTitle='" + mTitle + '\'' +
                ", mPrice=" + mPrice +
                ", mImageUrl='" + mImageUrl + '\'' +
                ", mQuantity=" + mQuantity +
                ", mCreated=" + mCreated +
                ", mShowShop=" + mShowShop +
                '}';
    }
}
