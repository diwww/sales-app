package ru.gcsales.app.data.model.remote;


import com.google.gson.annotations.SerializedName;


/**
 * Shop JSON response
 *
 * @author Maxim Surovtsev
 * @since 05/01/2019
 */
public class ShopResponse {
    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("image_url")
    private String mImageUrl;

    public long getId() {
        return mId;
    }

    public ShopResponse setId(long id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public ShopResponse setName(String name) {
        mName = name;
        return this;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public ShopResponse setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
        return this;
    }
}
