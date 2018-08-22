package ru.gcsales.app.presentation.model;

/**
 * @author Maxim Surovtsev
 * Created on 8/22/18
 */
public class CategoryViewModel {

    private String name;

    public CategoryViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
