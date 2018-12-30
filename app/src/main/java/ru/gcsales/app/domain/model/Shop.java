package ru.gcsales.app.domain.model;

/**
 * Shop domain model.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class Shop {
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
