package ru.gcsales.app.domain.model;

/**
 * Domain model to represent a product.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class Product {

    private long mId;
    private String mName;
    private String mCategory;
    private double mOldPrice;
    private double mNewPrice;
    private String mDateIn;
    private String mDateOut;
    private String mCrawlDate;
    private String mCondition;
    private String mImageUrl;
    private String mDiscount;
    private Shop mShop;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public double getOldPrice() {
        return mOldPrice;
    }

    public void setOldPrice(double oldPrice) {
        mOldPrice = oldPrice;
    }

    public double getNewPrice() {
        return mNewPrice;
    }

    public void setNewPrice(double newPrice) {
        mNewPrice = newPrice;
    }

    public String getDateIn() {
        return mDateIn;
    }

    public void setDateIn(String dateIn) {
        mDateIn = dateIn;
    }

    public String getDateOut() {
        return mDateOut;
    }

    public void setDateOut(String dateOut) {
        mDateOut = dateOut;
    }

    public String getCrawlDate() {
        return mCrawlDate;
    }

    public void setCrawlDate(String crawlDate) {
        mCrawlDate = crawlDate;
    }

    public String getCondition() {
        return mCondition;
    }

    public void setCondition(String condition) {
        mCondition = condition;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getDiscount() {
        return mDiscount;
    }

    public void setDiscount(String discount) {
        mDiscount = discount;
    }

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        mShop = shop;
    }
}
