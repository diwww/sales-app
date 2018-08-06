package ru.gcsales.app.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents JSON response for getting products
 * with pagination.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class ProductsInfoEntity {
    @SerializedName("count")
    @Expose
    private long mCount;
    @SerializedName("rows")
    @Expose
    private List<ProductEntity> mProductEntities = null;
    @SerializedName("numPages")
    @Expose
    private long mNumPages;

    public long getCount() {
        return mCount;
    }

    public void setCount(long count) {
        this.mCount = count;
    }

    public List<ProductEntity> getProductEntities() {
        return mProductEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.mProductEntities = productEntities;
    }

    public long getNumPages() {
        return mNumPages;
    }

    public void setNumPages(long numPages) {
        this.mNumPages = numPages;
    }
}
