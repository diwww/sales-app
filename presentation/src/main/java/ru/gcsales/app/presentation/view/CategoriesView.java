package ru.gcsales.app.presentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public interface CategoriesView extends MvpView {

    void showProgress();

    void hideProgress();

    void setData(List<String> data);

    void showError(String message);
}
