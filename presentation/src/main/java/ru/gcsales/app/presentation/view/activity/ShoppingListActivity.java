package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;
import ru.gcsales.app.presentation.presenter.ShoppingListPresenter;
import ru.gcsales.app.presentation.view.ShoppingListView;
import ru.gcsales.app.presentation.model.ItemViewModel;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter.OnButtonClickListener;

public class ShoppingListActivity extends MvpAppCompatActivity
        implements ShoppingListView, OnButtonClickListener {

    public static final String EXTRA_SHOPPING_LIST_ID = "EXTRA_SHOPPING_LIST_ID";
    public static final String EXTRA_SHOPPING_LIST_NAME = "EXTRA_SHOPPING_LIST_NAME";

    @InjectPresenter
    ShoppingListPresenter mShoppingListPresenter;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.text_total_price) TextView mTotalPriceTextView;
    @BindView(R.id.recycler_view_items) RecyclerView mRecyclerView;

    LinearLayoutManager mLinearLayoutManager;
    ItemsAdapter mItemsAdapter;

    @ProvidePresenter
    ShoppingListPresenter provideShoppingListPresenter() {
        long id = getIntent().getLongExtra(EXTRA_SHOPPING_LIST_ID, 0);
        return new ShoppingListPresenter(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        setTitle(getIntent().getStringExtra(EXTRA_SHOPPING_LIST_NAME));
        mToolbar.setOnClickListener(v -> mRecyclerView.smoothScrollToPosition(0));
        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mItemsAdapter = new ItemsAdapter(this, R.drawable.ic_remove_black_24dp);
        mRecyclerView.setAdapter(mItemsAdapter);

        mShoppingListPresenter.loadData();
    }


    @Override
    public void setData(ShoppingListViewModel shoppingList) {
        mItemsAdapter.setData(shoppingList.getItems());
        mTotalPriceTextView.setText(getString(R.string.total_price_text, mItemsAdapter.getTotalPrice()));
    }

    @Override
    public void deleteItem(ItemViewModel item) {
        mItemsAdapter.deleteItem(item);
        mTotalPriceTextView.setText(getString(R.string.total_price_text, mItemsAdapter.getTotalPrice()));
    }

    @Override
    public void onButtonClicked(ItemViewModel item) {
        mShoppingListPresenter.deleteItem(item);
    }

    public static Intent newIntent(Context context, long id, String name) {
        Intent intent = new Intent(context, ShoppingListActivity.class);
        intent.putExtra(EXTRA_SHOPPING_LIST_ID, id);
        intent.putExtra(EXTRA_SHOPPING_LIST_NAME, name);
        return intent;
    }
}
