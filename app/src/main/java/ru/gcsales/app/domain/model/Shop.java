package ru.gcsales.app.domain.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Shop
 *
 * @author Maxim Surovtsev
 * @since 05/01/2019
 */
public class Shop implements Serializable {

    private long mId;
    private String mName;
    private String mImageUrl;
    private List<String> mCategories;

    public long getId() {
        return mId;
    }

    public Shop setId(long id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Shop setName(String name) {
        mName = name;
        return this;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public Shop setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
        return this;
    }

    public List<String> getCategories() {
        return mCategories;
    }

    public Shop setCategories(List<String> categories) {
        mCategories = categories;
        return this;
    }
}
