package ru.gcsales.app.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

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

    @InjectPresenter
    ProductListPresenter mProductListPresenter;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler_view_products) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    ProductsAdapter mProductsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mProductsAdapter = new ProductsAdapter();
        mRecyclerView.setAdapter(mProductsAdapter);

        long shopId = getIntent().getLongExtra(EXTRA_SHOP_ID, 0);
        mProductListPresenter.loadProducts(shopId);
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
    public void setProducts(List<ProductModel> products) {
        mProductsAdapter.setData(products);
    }

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra(EXTRA_SHOP_ID, id);
        return intent;
    }
}
