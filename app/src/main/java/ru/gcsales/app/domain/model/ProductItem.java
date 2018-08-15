package ru.gcsales.app.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Class that represents JSON product entity.
 *
 * @author Maxim Surovtsev
 * Created on 7/26/18
 */
public class ProductItem implements Item {

    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("category")
    private String category;
    @SerializedName("oldPrice")
    private double oldPrice;
    @SerializedName("newPrice")
    private double newPrice;
    @SerializedName("dateIn")
    private String dateIn;
    @SerializedName("dateOut")
    private String dateOut;
    @SerializedName("crawlDate")
    private String crawlDate;
    @SerializedName("condition")
    private String condition;
    @SerializedName("image")
    private Object image;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("discount")
    private String discount;
    @SerializedName("mShop")
    private Shop mShop;

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

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
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

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        this.mShop = shop;
    }

    @Override
    public int getType() {
        return PRODUCT_TYPE;
    }
}
