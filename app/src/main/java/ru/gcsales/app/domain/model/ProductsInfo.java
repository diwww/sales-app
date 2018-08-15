package ru.gcsales.app.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents JSON response for getting mProductItems
 * with pagination.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class ProductsInfo {
    @SerializedName("count")
    private long mCount;
    @SerializedName("rows")
    private List<ProductItem> mProductItems = null;
    @SerializedName("numPages")

    private long mNumPages;

    public long getCount() {
        return mCount;
    }

    public void setCount(long count) {
        this.mCount = count;
    }

    public List<ProductItem> getProductItems() {
        return mProductItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.mProductItems = productItems;
    }

    public long getNumPages() {
        return mNumPages;
    }

    public void setNumPages(long numPages) {
        this.mNumPages = numPages;
    }
}
