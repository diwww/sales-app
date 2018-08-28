package ru.gcsales.app.presentation.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;
import ru.gcsales.app.presentation.presenter.ItemsPresenter;
import ru.gcsales.app.presentation.view.ItemsView;
import ru.gcsales.app.presentation.model.ItemViewModel;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter.OnButtonClickListener;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter;

public class ItemsActivity extends MvpAppCompatActivity implements ItemsView, OnButtonClickListener {

    private static final String EXTRA_SHOP_ID = "EXTRA_SHOP_ID";
    private static final String EXTRA_SHOP_NAME = "EXTRA_SHOP_NAME";
    private static final String EXTRA_CATEGORY = "EXTRA_CATEGORY";

    @InjectPresenter
    ItemsPresenter mItemsPresenter;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler_view_products) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    private ItemsAdapter mItemsAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private List<ShoppingListViewModel> mShoppingListViewModels;

    @ProvidePresenter
    ItemsPresenter provideProductListPresenter() {
        long shopId = getIntent().getLongExtra(EXTRA_SHOP_ID, 0);
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);
        return new ItemsPresenter(shopId, category);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        setTitle(getIntent().getStringExtra(EXTRA_SHOP_NAME));
        mToolbar.setOnClickListener(v -> mRecyclerView.smoothScrollToPosition(0));
        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mItemsAdapter = new ItemsAdapter(this, R.drawable.ic_add_black_24dp);
        mRecyclerView.setAdapter(mItemsAdapter);
        setOnScrollListener();
    }

    @Override
    public void showInitialProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInitialProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showPageProgress() {
        mItemsAdapter.showProgress();
    }

    @Override
    public void hidePageProgress() {
        mItemsAdapter.hideProgress();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showItemAdded(long shoppingListId, String shoppingListName) {
        Snackbar.make(mRecyclerView, getString(R.string.item_added_text, shoppingListName), Snackbar.LENGTH_SHORT)
                .setAction(R.string.open_text, v ->
                        startActivity(ShoppingListActivity.newIntent(ItemsActivity.this,
                                shoppingListId, shoppingListName))
                )
                .show();
    }

    @Override
    public void addItems(List<ItemViewModel> items) {
        mItemsAdapter.addData(items);
    }

    @Override
    public void setItems(List<ItemViewModel> items) {
        mItemsAdapter.setData(items);
    }

    @Override
    public void clearProducts() {
        mItemsAdapter.clear();
    }

    @Override
    public void setShoppingLists(List<ShoppingListViewModel> shoppingListViewModels) {
        mShoppingListViewModels = shoppingListViewModels;
    }

    @Override
    public void onButtonClicked(ItemViewModel itemViewModel) {
        showAddDialog(itemViewModel);
    }

    private void showAddDialog(ItemViewModel itemViewModel) {
        ArrayAdapter<ShoppingListViewModel> adapter = new ArrayAdapter<>(this,
                R.layout.item_shopping_list_dialog, mShoppingListViewModels);

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.choose_shopping_list)
                .setAdapter(adapter, (dialog, which) -> {
                    mItemsPresenter.addItem(itemViewModel, adapter.getItem(which));
                });

        builder.create().show();
    }

    private void setOnScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItems = mLinearLayoutManager.getItemCount();
                int lastVisibleItemIndex = mLinearLayoutManager.findLastVisibleItemPosition();
                mItemsPresenter.onScrolled(totalItems, lastVisibleItemIndex);
            }
        });
    }

    public static Intent newIntent(Context context, long shopId, String shopName, String category) {
        Intent intent = new Intent(context, ItemsActivity.class);
        intent.putExtra(EXTRA_SHOP_ID, shopId);
        intent.putExtra(EXTRA_SHOP_NAME, shopName);
        intent.putExtra(EXTRA_CATEGORY, category);
        return intent;
    }
}
