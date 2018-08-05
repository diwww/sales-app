package ru.gcsales.app.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import ru.gcsales.app.presentation.mvp.model.ProductModel;
import ru.gcsales.app.presentation.mvp.presenter.ProductListPresenter;
import ru.gcsales.app.presentation.mvp.view.ProductListView;
import ru.gcsales.app.presentation.ui.adapter.ProductsAdapter;

public class ProductListActivity extends MvpAppCompatActivity implements ProductListView {

    public static final String EXTRA_SHOP_ID = "EXTRA_SHOP_ID";
    public static final String EXTRA_SHOP_NAME = "EXTRA_SHOP_NAME";

    @InjectPresenter
    ProductListPresenter mProductListPresenter;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler_view_products) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    ProductsAdapter mProductsAdapter;
    LinearLayoutManager mLinearLayoutManager;

    @ProvidePresenter
    ProductListPresenter provideProductListPresenter() {
        long shopId = getIntent().getLongExtra(EXTRA_SHOP_ID, 0);
        return new ProductListPresenter(shopId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        setTitle(getIntent().getStringExtra(EXTRA_SHOP_NAME));
        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mProductsAdapter = new ProductsAdapter();
        mRecyclerView.setAdapter(mProductsAdapter);
        setOnScrollListener();

        mProductListPresenter.loadUnfiltered();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_select_category) {
            mProductListPresenter.loadCategory("Детское питание");
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
    public void addProducts(List<ProductModel> products) {
        mProductsAdapter.addData(products);
        Toast.makeText(this, "Count: " + mProductsAdapter.getItemCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProducts(List<ProductModel> products) {
        mProductsAdapter.setData(products);
    }

    @Override
    public void clearProducts() {
        mProductsAdapter.clear();
    }

    private void setOnScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItems = mLinearLayoutManager.getItemCount();
                int lastVisibleItemIndex = mLinearLayoutManager.findLastVisibleItemPosition();
                mProductListPresenter.onScrolled(totalItems, lastVisibleItemIndex);
            }
        });
    }

    public static Intent newIntent(Context context, long id, String name) {
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra(EXTRA_SHOP_ID, id);
        intent.putExtra(EXTRA_SHOP_NAME, name);
        return intent;
    }
}
