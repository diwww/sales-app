package ru.gcsales.app.presentation.ui.shops;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.leodroidcoder.genericadapter.OnEntityClickListener;

import java.util.List;

import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.presenter.ShopsPresenter;
import ru.gcsales.app.presentation.ui.base.BaseFragment;
import ru.gcsales.app.presentation.ui.categories.CategoriesActivity;

/**
 * Fragment for displaying available shops.
 */
public class ShopsFragment extends BaseFragment implements ShopsView, OnEntityClickListener<Shop> {

    @InjectPresenter
    ShopsPresenter mShopsPresenter;

    private ShopsAdapter mShopsAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mShopsAdapter = new ShopsAdapter(getActivity(), this);
        mShopsAdapter.setListener(this);
        mRecyclerView.setAdapter(mShopsAdapter);
    }

    @Override
    public void showShops(List<Shop> shops) {
        mShopsAdapter.setItems(shops);
    }

    @Override
    public void onItemClicked(Shop item) {
        startActivity(CategoriesActivity.newIntent(getActivity(), item));
    }

    /**
     * Creates new fragment instance
     *
     * @return new fragment instance
     */
    public static ShopsFragment newInstance() {
        ShopsFragment shopsFragment = new ShopsFragment();
        return shopsFragment;
    }
}
