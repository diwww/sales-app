package ru.gcsales.app;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for storing product model
 */
public class Product {

    @SerializedName("id")
    private int mId;
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
    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("discount")
    private String mDiscount;
    @SerializedName("shop")
    private Shop mShop;

    public Product(int id, String name, String category,
                   double oldPrice, double newPrice, String dateIn,
                   String dateOut, String crawlDate, String condition,
                   String imageUrl, String discount, Shop shop) {
        mId = id;
        mName = name;
        mCategory = category;
        mOldPrice = oldPrice;
        mNewPrice = newPrice;
        mDateIn = dateIn;
        mDateOut = dateOut;
        mCrawlDate = crawlDate;
        mCondition = condition;
        mImageUrl = imageUrl;
        mDiscount = discount;
        mShop = shop;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
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

    /**
     * Gets a {@link Product} object from JSON
     *
     * @param object JSON object to be parsed
     * @return new {@link Product} instance
     * @throws JSONException if an object cannot be parsed
     */
    public static Product fromJSON(JSONObject object) throws JSONException {
        int id = object.getInt("id");
        String name = object.getString("name");
        String category = object.getString("category");
        double oldPrice = object.getDouble("oldPrice");
        double newPrice = object.getDouble("newPrice");
        String dateIn = object.getString("dateIn");
        String dateOut = object.getString("dateOut");
        String crawlDate = object.getString("crawlDate");
        String condition = object.getString("condition");
        String imageUrl = object.getString("imageUrl");
        String discount = object.getString("discount");
        Shop shop = Shop.fromJSON(object.getJSONObject("shop"));

        return new Product(id, name, category,
                oldPrice, newPrice, dateIn,
                dateOut, crawlDate, condition,
                imageUrl, discount, shop);
    }
}
