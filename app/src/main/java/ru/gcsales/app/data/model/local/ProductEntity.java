package ru.gcsales.app.data.model.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


/**
 * @author Maxim Surovtsev
 * Created on 8/12/18
 */
@Entity(tableName = "product", foreignKeys = @ForeignKey(entity = ShopEntity.class, parentColumns = "id",
        childColumns = "shop_id"))
public class ProductEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "category")
    private String mCategory;
    @ColumnInfo(name = "oldPrice")
    private double mOldPrice;
    @ColumnInfo(name = "newPrice")
    private double mNewPrice;
    @ColumnInfo(name = "dateIn")
    private String mDateIn;
    @ColumnInfo(name = "dateOut")
    private String mDateOut;
    @ColumnInfo(name = "crawlDate")
    private String mCrawlDate;
    @ColumnInfo(name = "condition")
    private String mCondition;
    @ColumnInfo(name = "imageUrl")
    private String mImageUrl;
    @ColumnInfo(name = "discount")
    private String mDiscount;

    @ColumnInfo(name = "shop_id")
    private long mShopId;

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

    public long getShopId() {
        return mShopId;
    }

    public void setShopId(long shopId) {
        mShopId = shopId;
    }
}
