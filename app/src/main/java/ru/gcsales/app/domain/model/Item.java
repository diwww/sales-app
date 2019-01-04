package ru.gcsales.app.domain.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Item
 *
 * @author Maxim Surovtsev
 * Created on 7/26/18
 */
public class Item implements Serializable {

    private long mId;
    private String mName;
    private Category mCategory;
    private double mOldPrice;
    private double mNewPrice;
    private Date mDateIn;
    private Date mDateOut;
    private String mCrawlDate;
    private String mCondition;
    private String mImageUrl;
    private String mDiscount;
    private Shop mShop;

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

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
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

    public Date getDateIn() {
        return mDateIn;
    }

    public void setDateIn(Date dateIn) {
        this.mDateIn = dateIn;
    }

    public Date getDateOut() {
        return mDateOut;
    }

    public void setDateOut(Date dateOut) {
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

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        this.mShop = shop;
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
                ", mCategory=" + mCategory +
                ", mOldPrice=" + mOldPrice +
                ", mNewPrice=" + mNewPrice +
                ", mDateIn=" + mDateIn +
                ", mDateOut=" + mDateOut +
                ", mCrawlDate='" + mCrawlDate + '\'' +
                ", mCondition='" + mCondition + '\'' +
                ", mImageUrl='" + mImageUrl + '\'' +
                ", mDiscount='" + mDiscount + '\'' +
                ", mShop=" + mShop +
                '}';
    }
}
