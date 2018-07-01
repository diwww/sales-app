package ru.gcsales.app;

/**
 * Shop model
 *
 */
class Shop {

    private int mId;
    private String mAlias;
    private String mName;
    private String mImageUrl;

    public Shop(int id, String alias, String name, String imageUrl) {
        mId = id;
        mAlias = alias;
        mName = name;
        mImageUrl = imageUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
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
