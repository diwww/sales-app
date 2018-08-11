package ru.gcsales.app.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.gcsales.app.domain.model.ShoppingListPreview;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListPreviewView extends MvpView {

    void setData(List<ShoppingListPreview> data);

    void addData(ShoppingListPreview data);

    void showProgress();

    void hideProgress();

    void showError(String error);
}
