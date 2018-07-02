package ru.gcsales.app;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for storing shop model
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

    /**
     * Gets a {@link Shop} object from JSON
     *
     * @param object JSON object to be parsed
     * @return new {@link Shop} instance
     * @throws JSONException if an object cannot be parsed
     */
    public static Shop fromJSON(JSONObject object) throws JSONException {
        int id = object.getInt("id");
        String alias = object.getString("alias");
        String name = object.getString("name");
        String imageUrl = object.getString("imageUrl");

        return new Shop(id, alias, name, imageUrl);
    }
}
