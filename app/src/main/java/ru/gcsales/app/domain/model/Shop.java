package ru.gcsales.app.domain.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Shop
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class Shop implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Shop)) {
            return false;
        }
        Shop shop = (Shop) o;
        return mId == shop.mId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "mId=" + mId +
                ", mAlias='" + mAlias + '\'' +
                ", mName='" + mName + '\'' +
                ", mImageUrl='" + mImageUrl + '\'' +
                '}';
    }
}
