package ru.gcsales.app.data.model.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author Maxim Surovtsev
 * Created on 8/12/18
 */
@Entity(tableName = "shop")
public class ShopEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "alias")
    private String mAlias;
    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "image_url")
    private String mImageUrl;

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
