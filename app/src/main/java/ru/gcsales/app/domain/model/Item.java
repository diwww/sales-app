package ru.gcsales.app.domain.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Item
 *
 * @author Maxim Surovtsev
 * @since 05/01/2019
 */
public class Item implements Serializable {

    private long mId;
    private String mName;
    private double mOldPrice;
    private double mNewPrice;
    private String mDiscount;
    private String mConditions;
    private Date mTill;
    private String mImageUrl;

    public long getId() {
        return mId;
    }

    public Item setId(long id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Item setName(String name) {
        mName = name;
        return this;
    }

    public double getOldPrice() {
        return mOldPrice;
    }

    public Item setOldPrice(double oldPrice) {
        mOldPrice = oldPrice;
        return this;
    }

    public double getNewPrice() {
        return mNewPrice;
    }

    public Item setNewPrice(double newPrice) {
        mNewPrice = newPrice;
        return this;
    }

    public String getDiscount() {
        return mDiscount;
    }

    public Item setDiscount(String discount) {
        mDiscount = discount;
        return this;
    }

    public String getConditions() {
        return mConditions;
    }

    public Item setConditions(String conditions) {
        mConditions = conditions;
        return this;
    }

    public Date getTill() {
        return mTill;
    }

    public Item setTill(Date till) {
        mTill = till;
        return this;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public Item setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return mId == item.mId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId);
    }

    @Override
    public String toString() {
        return "Item{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mOldPrice=" + mOldPrice +
                ", mNewPrice=" + mNewPrice +
                ", mDiscount='" + mDiscount + '\'' +
                ", mConditions='" + mConditions + '\'' +
                ", mTill=" + mTill +
                ", mImageUrl='" + mImageUrl + '\'' +
                '}';
    }
}
