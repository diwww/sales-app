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
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.presentation.presenter.ShoppingListPresenter;
import ru.gcsales.app.presentation.view.ShoppingListView;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter.OnButtonClickListener;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter;

public class ShoppingListActivity extends MvpAppCompatActivity implements ShoppingListView, OnButtonClickListener {

    public static final String EXTRA_SHOPPING_LIST_ID = "EXTRA_SHOPPING_LIST_ID";

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
//        setTitle(getIntent().getStringExtra(EXTRA_SHOP_NAME));
//        mToolbar.setOnClickListener(v -> mRecyclerView.smoothScrollToPosition(0));
        mTotalPriceTextView.setText(getString(R.string.total_price_text, 534.87));
        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mItemsAdapter = new ItemsAdapter(ItemsAdapter.ActionButtonIcon.REMOVE, this);
        mRecyclerView.setAdapter(mItemsAdapter);

        mShoppingListPresenter.loadData();
    }

    @Override
    public void setData(ShoppingList shoppingList) {
        // TODO: add custom items
        mItemsAdapter.setData(shoppingList.getItems());
    }

    @Override
    public void deleteItem(Item item) {
        mItemsAdapter.deleteItem(item);
    }

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, ShoppingListActivity.class);
        intent.putExtra(EXTRA_SHOPPING_LIST_ID, id);
        return intent;
    }

    @Override
    public void onButtonClicked(Item item) {
        // TODO: delete item
        mShoppingListPresenter.deleteItem(item);
    }
}
