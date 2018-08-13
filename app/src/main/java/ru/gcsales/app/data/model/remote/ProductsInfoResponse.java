package ru.gcsales.app.data.model.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents JSON response for getting products
 * with pagination.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class ProductsInfoResponse {
    @SerializedName("count")
    private long mCount;
    @SerializedName("rows")
    private List<ProductResponse> mProductRespons = null;
    @SerializedName("numPages")

    private long mNumPages;

    public long getCount() {
        return mCount;
    }

    public void setCount(long count) {
        this.mCount = count;
    }

    public List<ProductResponse> getProductRespons() {
        return mProductRespons;
    }

    public void setProductRespons(List<ProductResponse> productRespons) {
        this.mProductRespons = productRespons;
    }

    public long getNumPages() {
        return mNumPages;
    }

    public void setNumPages(long numPages) {
        this.mNumPages = numPages;
    }
}
