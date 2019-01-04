package ru.gcsales.app.presentation.ui.shops;

import java.util.List;

import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.ui.base.BaseView;

/**
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
public interface ShopsView extends BaseView {

    void showShops(List<Shop> shops);
}
