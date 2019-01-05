package ru.gcsales.app.presentation.ui.categories;

import java.util.List;

import ru.gcsales.app.presentation.ui.base.BaseView;

/**
 * Categories view
 *
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
public interface CategoriesView extends BaseView {

    /**
     * Shows categories data
     *
     * @param categories categories data to be shown
     */
    void showCategories(List<String> categories);
}
