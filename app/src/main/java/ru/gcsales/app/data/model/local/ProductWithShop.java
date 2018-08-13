package ru.gcsales.app.data.model.local;

import android.arch.persistence.room.ColumnInfo;

/**
 * @author Maxim Surovtsev
 * Created on 8/13/18
 */
public class ProductWithShop {

    private long id;
    private String name;
    private String category;
    private double oldPrice;
    private double newPrice;
    private String dateIn;
    private String dateOut;
    private String crawlDate;
    private String condition;
    private String imageUrl;
    private String discount;

    @ColumnInfo(name = "shop_id")
    private long mShopId;
    @ColumnInfo(name = "shop_name")
    private String mShopName;
    @ColumnInfo(name = "shop_alias")
    private String mShopAlias;
    @ColumnInfo(name = "shop_image_url")
    private String mShopImageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getCrawlDate() {
        return crawlDate;
    }

    public void setCrawlDate(String crawlDate) {
        this.crawlDate = crawlDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public long getShopId() {
        return mShopId;
    }

    public void setShopId(long shopId) {
        mShopId = shopId;
    }

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        mShopName = shopName;
    }

    public String getShopAlias() {
        return mShopAlias;
    }

    public void setShopAlias(String shopAlias) {
        mShopAlias = shopAlias;
    }

    public String getShopImageUrl() {
        return mShopImageUrl;
    }

    public void setShopImageUrl(String shopImageUrl) {
        mShopImageUrl = shopImageUrl;
    }
}
