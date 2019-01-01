package ru.gcsales.app.domain.model;

import java.util.Objects;

/**
 * Category
 *
 * @author Maxim Surovtsev
 * Created on 8/22/18
 */
public class Category {

    private long mId;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return mId == category.mId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
