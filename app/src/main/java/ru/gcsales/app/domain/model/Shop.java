package ru.gcsales.app.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Class that represents JSON shop entity.
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class Shop {
    @SerializedName("id")
    private long id;
    @SerializedName("alias")
    private String alias;
    @SerializedName("name")
    private String name;
    @SerializedName("imageUrl")
    private String imageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
