package ru.gcsales.app.data.model.remote;


import com.google.gson.annotations.SerializedName;

/**
 * Shop JSON response.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class ShopResponse {
    @SerializedName("id")
    private long mId;
    @SerializedName("alias")
    private String mAlias;
    @SerializedName("name")
    private String mName;
    @SerializedName("imageUrl")
    private String mImageUrl;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getAlias() {
        return mAlias;
    }

    public void setAlias(String alias) {
        this.mAlias = alias;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}
