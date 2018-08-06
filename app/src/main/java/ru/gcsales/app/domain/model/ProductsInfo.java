package ru.gcsales.app.domain.model;


import java.util.List;

/**
 * Domain model to represent products info.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class ProductsInfo {

    private long mCount;
    private List<Product> mProducts = null;
    private long mNumPages;

    public long getCount() {
        return mCount;
    }

    public void setCount(long count) {
        this.mCount = count;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    public long getNumPages() {
        return mNumPages;
    }

    public void setNumPages(long numPages) {
        this.mNumPages = numPages;
    }
}
