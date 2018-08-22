package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import ru.gcsales.app.presentation.model.CategoryViewModel;
import ru.gcsales.app.presentation.presenter.CategoriesPresenter;
import ru.gcsales.app.presentation.view.CategoriesView;
import ru.gcsales.app.presentation.view.adapter.CategoriesAdapter;
import ru.gcsales.app.presentation.view.adapter.CategoriesAdapter.OnItemClickListener;

public class CategoriesActivity extends MvpAppCompatActivity implements CategoriesView, OnItemClickListener {

    private static final String EXTRA_SHOP_ID = "EXTRA_SHOP_ID";
    private static final String EXTRA_SHOP_NAME = "EXTRA_SHOP_NAME";

    @InjectPresenter
    CategoriesPresenter mCategoriesPresenter;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.recycler_view_categories) RecyclerView mRecyclerView;

    private CategoriesAdapter mCategoriesAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private long mShopId;
    private String mShopName;

    @ProvidePresenter
    CategoriesPresenter provideCategoriesPresenter() {
        return new CategoriesPresenter(mShopId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mShopId = getIntent().getLongExtra(EXTRA_SHOP_ID, 0);
        mShopName = getIntent().getStringExtra(EXTRA_SHOP_NAME);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        setTitle(mShopName);

        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mCategoriesAdapter = new CategoriesAdapter(this);
        mRecyclerView.setAdapter(mCategoriesAdapter);

        mCategoriesPresenter.loadCategories();
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
    public void setData(List<CategoryViewModel> data) {
        mCategoriesAdapter.setData(data);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(CategoryViewModel category) {
        startActivity(ItemsActivity.newIntent(this, mShopId, mShopName, category.getName()));
    }

    public static Intent newIntent(Context context, long shopId, String shopName) {
        Intent intent = new Intent(context, CategoriesActivity.class);
        intent.putExtra(EXTRA_SHOP_ID, shopId);
        intent.putExtra(EXTRA_SHOP_NAME, shopName);
        return intent;
    }
}
