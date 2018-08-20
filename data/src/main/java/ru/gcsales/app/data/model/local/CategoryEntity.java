package ru.gcsales.app.data.model.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
@Entity(tableName = "category", foreignKeys = @ForeignKey(entity = ShopEntity.class,
        parentColumns = "id", childColumns = "shop_id", onDelete = CASCADE))
public class CategoryEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "shop_id")
    private long mShopId;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getShopId() {
        return mShopId;
    }

    public void setShopId(long shopId) {
        mShopId = shopId;
    }
}
