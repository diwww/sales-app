package ru.gcsales.app.domain.model;

/**
 * @author Maxim Surovtsev
 * Created on 8/14/18
 */
public interface Item {

    int PRODUCT_TYPE = 1;
    int CUSTOM_ITEM_TYPE = 2;

    int getType();
}
