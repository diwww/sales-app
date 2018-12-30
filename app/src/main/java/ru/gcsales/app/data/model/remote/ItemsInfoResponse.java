package ru.gcsales.app.data.model.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Items info JSON response.
 *
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class ItemsInfoResponse {
    @SerializedName("count")
    private long mCount;
    @SerializedName("rows")
    private List<ItemResponse> mItemResponses = null;
    @SerializedName("numPages")

    private long mNumPages;

    public long getCount() {
        return mCount;
    }

    public void setCount(long count) {
        this.mCount = count;
    }

    public List<ItemResponse> getItemResponses() {
        return mItemResponses;
    }

    public void setItemResponses(List<ItemResponse> itemResponses) {
        this.mItemResponses = itemResponses;
    }

    public long getNumPages() {
        return mNumPages;
    }

    public void setNumPages(long numPages) {
        this.mNumPages = numPages;
    }
}
