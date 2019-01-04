package ru.gcsales.app.presentation.ui.categories;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.leodroidcoder.genericadapter.OnEntityClickListener;

import java.util.List;

import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.presenter.CategoriesPresenter;
import ru.gcsales.app.presentation.ui.base.BaseFragment;
import ru.gcsales.app.presentation.ui.items.ItemsActivity;

import static ru.gcsales.app.presentation.Constants.EXTRA_SHOP;

/**
 * Categories fragment
 *
 * @author Maxim Surovtsev
 * @since 03/01/2019
 */
public class CategoriesFragment extends BaseFragment implements CategoriesView, OnEntityClickListener<Category> {

    @InjectPresenter
    CategoriesPresenter mCategoriesPresenter;

    private Shop mShop;
    private CategoriesAdapter mCategoriesAdapter;

    @ProvidePresenter
    CategoriesPresenter provideCategoriesPresenter() {
        return new CategoriesPresenter(mShop);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mShop = (Shop) getArguments().getSerializable(EXTRA_SHOP);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mCategoriesAdapter = new CategoriesAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mCategoriesAdapter);
    }

    @Override
    public void showCategories(List<Category> categories) {
        mCategoriesAdapter.setItems(categories);
    }

    @Override
    public void showItemsScreen(Shop shop, Category category) {
        startActivity(ItemsActivity.newIntent(getActivity(), shop, category));
    }

    @Override
    public void onItemClicked(Category item) {
        showItemsScreen(mShop, item);
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
