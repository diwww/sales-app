package ru.gcsales.app.data.model.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Shopping list item many-to-many relation.
 *
 * @author Maxim Surovtsev
 * Created on 8/13/18
 */
@Entity(tableName = "shopping_list_item",
        foreignKeys = {
                @ForeignKey(entity = ShoppingListEntity.class, parentColumns = "id", childColumns = "shopping_list_id", onDelete = CASCADE),
                @ForeignKey(entity = ItemEntity.class, parentColumns = "id", childColumns = "item_id", onDelete = CASCADE)
        },
        primaryKeys = {"shopping_list_id", "item_id"})
public class ShoppingListItemEntity {

    @ColumnInfo(name = "shopping_list_id")
    private long mShoppingListId;
    @ColumnInfo(name = "item_id")
    private long mItemId;

    private int quantity;

    public ShoppingListItemEntity() {
    }

    @Ignore
    public ShoppingListItemEntity(long shoppingListId, long itemId) {
        mShoppingListId = shoppingListId;
        mItemId = itemId;
    }

    public long getShoppingListId() {
        return mShoppingListId;
    }

    public void setShoppingListId(long shoppingListId) {
        mShoppingListId = shoppingListId;
    }

    public long getItemId() {
        return mItemId;
    }

    public void setItemId(long itemId) {
        mItemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
