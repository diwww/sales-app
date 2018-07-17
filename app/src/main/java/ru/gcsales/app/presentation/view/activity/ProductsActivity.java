package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import ru.gcsales.app.R;
import ru.gcsales.app.presentation.presenter.ProductsPresenter;
import ru.gcsales.app.presentation.view.ProductsMvpView;
import ru.gcsales.app.presentation.view.adapter.ProductsAdapter;

public class ProductsActivity extends AppCompatActivity implements ProductsMvpView {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private ProductsAdapter mProductsAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ProductsPresenter mProductsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        init();
        mProductsPresenter = new ProductsPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProductsPresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mProductsPresenter.detachView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void init() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mRecyclerView = findViewById(R.id.recycler_view_products);

        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mProductsAdapter = new ProductsAdapter(this);
        mRecyclerView.setAdapter(mProductsAdapter);
    }

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, ProductsActivity.class);
        // TODO: put extra id and etc...
        return intent;
    }
}
