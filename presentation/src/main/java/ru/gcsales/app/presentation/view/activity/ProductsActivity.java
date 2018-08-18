package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.presentation.presenter.ProductsPresenter;
import ru.gcsales.app.presentation.view.ProductsView;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter.OnButtonClickListener;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter;

public class ProductsActivity extends MvpAppCompatActivity implements ProductsView, OnButtonClickListener {

    public static final String EXTRA_SHOP_ID = "EXTRA_SHOP_ID";
    public static final String EXTRA_SHOP_NAME = "EXTRA_SHOP_NAME";

    @InjectPresenter
    ProductsPresenter mProductsPresenter;

    // ProductItem list views
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler_view_products) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    ItemsAdapter mItemsAdapter;
    LinearLayoutManager mLinearLayoutManager;

    private AlertDialog mAlertDialog;

    @ProvidePresenter
    ProductsPresenter provideProductListPresenter() {
        long shopId = getIntent().getLongExtra(EXTRA_SHOP_ID, 0);
        return new ProductsPresenter(shopId);
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
        mItemsAdapter = new ItemsAdapter(ItemsAdapter.ActionButtonIcon.ADD, this);
        mRecyclerView.setAdapter(mItemsAdapter);
        setOnScrollListener();

        mProductsPresenter.loadUnfiltered();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_select_category) {
            mAlertDialog.show();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addProducts(List<Item> items) {
        mItemsAdapter.addData(items);
        Toast.makeText(this, "Count: " + mItemsAdapter.getItemCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProducts(List<Item> items) {
        mItemsAdapter.setData(items);
    }

    @Override
    public void clearProducts() {
        mItemsAdapter.clear();
    }

    private void initAlertDialog(String[] items) {
        mAlertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.select_category_text)
                .setItems(items, (dialog, which) -> {
                    if (which == 0) {
                        mProductsPresenter.loadCategory(null);
                    } else {
                        mProductsPresenter.loadCategory(items[which]);
                    }
                })
                .create();
    }

    private void setOnScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItems = mLinearLayoutManager.getItemCount();
                int lastVisibleItemIndex = mLinearLayoutManager.findLastVisibleItemPosition();
                mProductsPresenter.onScrolled(totalItems, lastVisibleItemIndex);
            }
        });
    }

    public static Intent newIntent(Context context, long id, String name) {
        Intent intent = new Intent(context, ProductsActivity.class);
        intent.putExtra(EXTRA_SHOP_ID, id);
        intent.putExtra(EXTRA_SHOP_NAME, name);
        return intent;
    }

    @Override
    public void onButtonClicked(Item item) {
        // TODO: show dialog
        mProductsPresenter.addItem(88, item.getId());
    }
}
