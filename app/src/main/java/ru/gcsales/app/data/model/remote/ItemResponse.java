package ru.gcsales.app.data.model.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Item JSON response
 *
 * @author Maxim Surovtsev
 * @since 05/01/2019
 */

public class ItemResponse {

    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("old_price")
    private double mOldPrice;
    @SerializedName("new_price")
    private double mNewPrice;
    @SerializedName("discount")
    private String mDiscount;
    @SerializedName("conditions")
    private String mConditions;
    @SerializedName("till")
    private String mTill;
    @SerializedName("image_url")
    private String mImageUrl;

    public long getId() {
        return mId;
    }

    public ItemResponse setId(long id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public ItemResponse setName(String name) {
        mName = name;
        return this;
    }

    public double getOldPrice() {
        return mOldPrice;
    }

    public ItemResponse setOldPrice(double oldPrice) {
        mOldPrice = oldPrice;
        return this;
    }

    public double getNewPrice() {
        return mNewPrice;
    }

    public ItemResponse setNewPrice(double newPrice) {
        mNewPrice = newPrice;
        return this;
    }

    public String getDiscount() {
        return mDiscount;
    }

    public ItemResponse setDiscount(String discount) {
        mDiscount = discount;
        return this;
    }

    public String getConditions() {
        return mConditions;
    }

    public ItemResponse setConditions(String conditions) {
        mConditions = conditions;
        return this;
    }

    public String getTill() {
        return mTill;
    }

    public ItemResponse setTill(String till) {
        mTill = till;
        return this;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public ItemResponse setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
        return this;
    }
}

