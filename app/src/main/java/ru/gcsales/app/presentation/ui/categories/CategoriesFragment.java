package ru.gcsales.app.presentation.ui.categories;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.leodroidcoder.genericadapter.OnEntityClickListener;

import java.util.List;

import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.presenter.CategoriesPresenter;
import ru.gcsales.app.presentation.ui.base.BaseFragment;

import static ru.gcsales.app.presentation.Constants.EXTRA_SHOP_ID;

/**
 * Categories fragment
 *
 * @author Maxim Surovtsev
 * @since 03/01/2019
 */
public class CategoriesFragment extends BaseFragment implements CategoriesView, OnEntityClickListener<String> {

    @InjectPresenter
    CategoriesPresenter mCategoriesPresenter;

    private CategoriesAdapter mCategoriesAdapter;

    @ProvidePresenter
    CategoriesPresenter provideCategoriesPresenter() {
        return new CategoriesPresenter(getArguments().getLong(EXTRA_SHOP_ID));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mCategoriesAdapter = new CategoriesAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mCategoriesAdapter);
    }

    @Override
    public void showCategories(List<String> categories) {
        mCategoriesAdapter.setItems(categories);
    }

    @Override
    public void onItemClicked(String item) {
        // TODO
    }

    /**
     * Gets a new fragment instance
     *
     * @param shopId {@link Shop} model
     * @return new fragment instance
     */
    public static CategoriesFragment newInstance(long shopId) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_SHOP_ID, shopId);
        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
