package ru.gcsales.app.data.model.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * @author Maxim Surovtsev
 * Created on 8/13/18
 */
@Entity(tableName = "shopping_list_product",
        foreignKeys = {
                @ForeignKey(entity = ShoppingListEntity.class, parentColumns = "id", childColumns = "shopping_list_id", onDelete = CASCADE),
                @ForeignKey(entity = ItemEntity.class, parentColumns = "id", childColumns = "product_id", onDelete = CASCADE)
        },
        primaryKeys = {"shopping_list_id", "product_id"})
public class ShoppingListProductEntity {

    @ColumnInfo(name = "shopping_list_id")
    private long mShoppingListId;
    @ColumnInfo(name = "product_id")
    private long mProductId;

    private int quantity;

    public ShoppingListProductEntity() {
    }

    @Ignore
    public ShoppingListProductEntity(long shoppingListId, long productId) {
        mShoppingListId = shoppingListId;
        mProductId = productId;
    }

    public long getShoppingListId() {
        return mShoppingListId;
    }

    public void setShoppingListId(long shoppingListId) {
        mShoppingListId = shoppingListId;
    }

    public long getProductId() {
        return mProductId;
    }

    public void setProductId(long productId) {
        mProductId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
