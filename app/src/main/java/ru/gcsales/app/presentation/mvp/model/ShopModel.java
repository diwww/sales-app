package ru.gcsales.app.presentation.mvp.model;

/**
 * Shop presentation model.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class ShopModel {

    private long mId;
    private String mAlias;
    private String mName;
    private String mImageUrl;

    public ShopModel() {
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getAlias() {
        return mAlias;
    }

    public void setAlias(String alias) {
        mAlias = alias;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
