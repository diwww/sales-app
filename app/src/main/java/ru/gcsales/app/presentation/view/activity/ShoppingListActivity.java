package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


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

public class ShoppingListActivity extends BaseActivity
        implements ShoppingListView, OnButtonClickListener {

    public static final String EXTRA_SHOPPING_LIST_ID = "EXTRA_SHOPPING_LIST_ID";
    public static final String EXTRA_SHOPPING_LIST_NAME = "EXTRA_SHOPPING_LIST_NAME";

    @InjectPresenter
    ShoppingListPresenter mShoppingListPresenter;

    @BindView(R.id.text_total_price) TextView mTotalPriceTextView;
    @BindView(R.id.recycler_view_items) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.text_stub) TextView mStubTextView;

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
        setToolbar(true);
        setTitle(getIntent().getStringExtra(EXTRA_SHOPPING_LIST_NAME));
        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mItemsAdapter = new ItemsAdapter(this, R.drawable.ic_remove_black_24dp);
        mRecyclerView.setAdapter(mItemsAdapter);
    }


    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setData(ShoppingListViewModel shoppingList) {
        if (shoppingList.getItems().size() > 0) { // If there is data
            mItemsAdapter.setData(shoppingList.getItems());
            mStubTextView.setVisibility(View.INVISIBLE);
        } else { // If there is no data
            mStubTextView.setVisibility(View.VISIBLE);
        }
        mTotalPriceTextView.setText(getString(R.string.total_price_text, mItemsAdapter.getTotalPrice()));
    }

    @Override
    public void deleteItem(ItemViewModel item) {
        mItemsAdapter.removeItem(item);
        mTotalPriceTextView.setText(getString(R.string.total_price_text, mItemsAdapter.getTotalPrice()));
        if (mItemsAdapter.getItemCount() == 0) { // Show stub if there are no items left
            mStubTextView.setVisibility(View.VISIBLE);
        }
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
