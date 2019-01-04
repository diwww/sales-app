package ru.gcsales.app.presentation.ui.categories;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.ui.base.BaseFragment;

import static ru.gcsales.app.presentation.Constants.EXTRA_SHOP;

/**
 * Categories fragment
 *
 * @author Maxim Surovtsev
 * @since 03/01/2019
 */
public class CategoriesFragment extends BaseFragment {

    private Shop mShop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mShop = (Shop) getArguments().getSerializable(EXTRA_SHOP);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * Gets a new fragment instance
     *
     * @param shop {@link Shop} model
     * @return new fragment instance
     */
    public static CategoriesFragment newInstance(Shop shop) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SHOP, shop);
        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
