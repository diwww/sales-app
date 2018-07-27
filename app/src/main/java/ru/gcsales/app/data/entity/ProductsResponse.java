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
public class ProductsResponse {
    @SerializedName("count")
    @Expose
    private long count;
    @SerializedName("rows")
    @Expose
    private List<ProductEntity> productEntities = null;
    @SerializedName("numPages")
    @Expose
    private long numPages;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

    public long getNumPages() {
        return numPages;
    }

    public void setNumPages(long numPages) {
        this.numPages = numPages;
    }
}
