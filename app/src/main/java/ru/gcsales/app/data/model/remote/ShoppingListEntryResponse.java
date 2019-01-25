package ru.gcsales.app.data.model.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Shopping list entry response
 *
 * @author Maxim Surovtsev
 * @since 07/01/2019
 */
public class ShoppingListEntryResponse {

    @SerializedName("shop")
    private String mShop;
    @SerializedName("item_id")
    private long mItemId;
    @SerializedName("image_url")
    private String mImageUrl;
    @SerializedName("price")
    private double mPrice;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("quantity")
    private int mQuantity;
    @SerializedName("created")
    private long mCreated;
    @SerializedName("show_shop")
    private boolean mShowShop;

    public String getShop() {
        return mShop;
    }

    public ShoppingListEntryResponse setShop(String shop) {
        mShop = shop;
        return this;
    }

    public long getItemId() {
        return mItemId;
    }

    public ShoppingListEntryResponse setItemId(long itemId) {
        mItemId = itemId;
        return this;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public ShoppingListEntryResponse setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
        return this;
    }

    public double getPrice() {
        return mPrice;
    }

    public ShoppingListEntryResponse setPrice(double price) {
        mPrice = price;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public ShoppingListEntryResponse setTitle(String title) {
        mTitle = title;
        return this;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public ShoppingListEntryResponse setQuantity(int quantity) {
        mQuantity = quantity;
        return this;
    }

    public long getCreated() {
        return mCreated;
    }

    public ShoppingListEntryResponse setCreated(long created) {
        mCreated = created;
        return this;
    }

    public boolean isShowShop() {
        return mShowShop;
    }

    public ShoppingListEntryResponse setShowShop(boolean showShop) {
        mShowShop = showShop;
        return this;
    }
}
