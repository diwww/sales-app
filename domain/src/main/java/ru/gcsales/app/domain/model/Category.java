package ru.gcsales.app.domain.model;

/**
 * Category domain model.
 *
 * @author Maxim Surovtsev
 * Created on 8/22/18
 */
public class Category {

    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
