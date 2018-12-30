package ru.gcsales.app.presentation.model;

/**
 * Shop view model.
 *
 * @author Maxim Surovtsev
 * Created on 8/28/18
 */
public class ShopViewModel {
    private long mId;
    private String mAlias;
    private String mName;
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
