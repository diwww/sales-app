package ru.gcsales.app.presentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.presentation.model.CategoryViewModel;

/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public interface CategoriesView extends MvpView {

    void showProgress();

    void hideProgress();

    void setData(List<CategoryViewModel> data);

    void showError(String message);
}
