package ru.gcsales.app.presentation.ui.shoppinglist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.leodroidcoder.genericadapter.OnEntityClickListener;

import java.util.List;

import ru.gcsales.app.domain.model.ShoppingListEntry;
import ru.gcsales.app.presentation.presenter.ShoppingListPresenter;
import ru.gcsales.app.presentation.ui.base.BaseFragment;


public class ShoppingListFragment extends BaseFragment implements ShoppingListView, OnEntityClickListener<ShoppingListEntry> {

    @InjectPresenter
    ShoppingListPresenter mShoppingListPresenter;
    private ShoppingListAdapter mShoppingListAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mShoppingListAdapter = new ShoppingListAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mShoppingListAdapter);
    }

    @Override
    public void showEntries(List<ShoppingListEntry> entries) {
        mShoppingListAdapter.setItems(entries);
    }

    @Override
    public void onItemClicked(ShoppingListEntry item) {

    }

    /**
     * Creates a new fragment instance.
     *
     * @return new fragment instance
     */
    public static ShoppingListFragment newInstance() {
        ShoppingListFragment fragment = new ShoppingListFragment();
        return fragment;
    }
}
