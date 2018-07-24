package ru.gcsales.app.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import ru.gcsales.app.R;
import ru.gcsales.app.presentation.ui.adapter.ProductsAdapter;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private ProductsAdapter mProductsAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
        Intent intent = new Intent(context, ProductListActivity.class);
        // TODO: put extra id and etc...
        return intent;
    }
}
