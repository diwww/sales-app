package ru.gcsales.app.data.model.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Item JSON response.
 *
 * @author Maxim Surovtsev
 * Created on 8/12/18
 */

public class ItemResponse {

    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("category")
    private String mCategory;
    @SerializedName("oldPrice")
    private double mOldPrice;
    @SerializedName("newPrice")
    private double mNewPrice;
    @SerializedName("dateIn")
    private String mDateIn;
    @SerializedName("dateOut")
    private String mDateOut;
    @SerializedName("crawlDate")
    private String mCrawlDate;
    @SerializedName("condition")
    private String mCondition;
    @SerializedName("image")
    private Object mImage;
    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("discount")
    private String mDiscount;
    @SerializedName("shop")
    private ShopResponse mShopResponse;

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

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public double getOldPrice() {
        return mOldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.mOldPrice = oldPrice;
    }

    public double getNewPrice() {
        return mNewPrice;
    }

    public void setNewPrice(double newPrice) {
        this.mNewPrice = newPrice;
    }

    public String getDateIn() {
        return mDateIn;
    }

    public void setDateIn(String dateIn) {
        this.mDateIn = dateIn;
    }

    public String getDateOut() {
        return mDateOut;
    }

    public void setDateOut(String dateOut) {
        this.mDateOut = dateOut;
    }

    public String getCrawlDate() {
        return mCrawlDate;
    }

    public void setCrawlDate(String crawlDate) {
        this.mCrawlDate = crawlDate;
    }

    public String getCondition() {
        return mCondition;
    }

    public void setCondition(String condition) {
        this.mCondition = condition;
    }

    public Object getImage() {
        return mImage;
    }

    public void setImage(Object image) {
        this.mImage = image;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getDiscount() {
        return mDiscount;
    }

    public void setDiscount(String discount) {
        this.mDiscount = discount;
    }

    public ShopResponse getShopResponse() {
        return mShopResponse;
    }

    public void setShopResponse(ShopResponse shopResponse) {
        this.mShopResponse = shopResponse;
    }
}

