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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.mvp.model.ProductModel;
import ru.gcsales.app.presentation.mvp.model.ShopInfoModel;
import ru.gcsales.app.presentation.mvp.presenter.ProductListPresenter;
import ru.gcsales.app.presentation.mvp.view.ProductListView;
import ru.gcsales.app.presentation.ui.adapter.ProductsAdapter;

public class ProductListActivity extends MvpAppCompatActivity implements ProductListView {

    public static final String EXTRA_SHOP_ID = "EXTRA_SHOP_ID";
    public static final String EXTRA_SHOP_NAME = "EXTRA_SHOP_NAME";

    @InjectPresenter
    ProductListPresenter mProductListPresenter;

    // Product list views
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler_view_products) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    // Shop info views
    @BindView(R.id.text_num_items) TextView mNumItemsTextView;
    //    @BindView(R.id.text_current_category) TextView mCurrentCategoryTextView;
    @BindView(R.id.image_shop_logo) CircleImageView mShopLogoImageView;

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
        mToolbar.setOnClickListener(v -> mRecyclerView.smoothScrollToPosition(0));
        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mProductsAdapter = new ProductsAdapter();
        mRecyclerView.setAdapter(mProductsAdapter);
        setOnScrollListener();

        mProductListPresenter.loadUnfiltered();
        mProductListPresenter.loadShopInfo();
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

    @Override
    public void setShopInfo(ShopInfoModel shopInfo) {
        // TODO: LinearLayout плавно появляется с анимацией
        mNumItemsTextView.setText(getString(R.string.num_items_text, shopInfo.getNumItems()));
        Glide.with(this)
                .load(shopInfo.getShop().getImageUrl())
                .into(mShopLogoImageView);
//        mCurrentCategoryTextView.setText(getString(R.string.current_category_text, "Овощи и фрукты"));
        Toast.makeText(this, "Categories: " + shopInfo.getCategories(), Toast.LENGTH_SHORT).show();
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
