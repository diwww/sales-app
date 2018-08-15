package ru.gcsales.app.presentation.ui.activity;

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
import ru.gcsales.app.domain.model.ProductItem;
import ru.gcsales.app.domain.model.ShopInfo;
import ru.gcsales.app.presentation.mvp.presenter.ProductsPresenter;
import ru.gcsales.app.presentation.mvp.view.ProductsView;
import ru.gcsales.app.presentation.ui.adapter.ItemsAdapter;

public class ProductsActivity extends MvpAppCompatActivity implements ProductsView {

    public static final String EXTRA_SHOP_ID = "EXTRA_SHOP_ID";
    public static final String EXTRA_SHOP_NAME = "EXTRA_SHOP_NAME";

    @InjectPresenter
    ProductsPresenter mProductsPresenter;

    // ProductItem list views
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler_view_products) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    // Shop info views
    @BindView(R.id.text_num_items) TextView mNumItemsTextView;
    @BindView(R.id.text_current_category) TextView mCurrentCategoryTextView;
    @BindView(R.id.image_shop_logo) CircleImageView mShopLogoImageView;

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
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        setTitle(getIntent().getStringExtra(EXTRA_SHOP_NAME));
        mToolbar.setOnClickListener(v -> mRecyclerView.smoothScrollToPosition(0));
        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mItemsAdapter = new ItemsAdapter(ItemsAdapter.ActionButtonIcon.ADD);
        mRecyclerView.setAdapter(mItemsAdapter);
        setOnScrollListener();

        mProductsPresenter.loadUnfiltered();
        mProductsPresenter.loadShopInfo();
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
    public void addProducts(List<ProductItem> productItems) {
        mItemsAdapter.addData(productItems);
        Toast.makeText(this, "Count: " + mItemsAdapter.getItemCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProducts(List<ProductItem> productItems) {
        mItemsAdapter.setData(productItems);
    }

    @Override
    public void clearProducts() {
        mItemsAdapter.clear();
    }

    @Override
    public void setShopInfo(ShopInfo shopInfo) {
        // TODO: LinearLayout плавно появляется с анимацией
        Glide.with(this)
                .load(shopInfo.getShop().getImageUrl())
                .into(mShopLogoImageView);

        List<String> categoriesList = shopInfo.getCategories();
        categoriesList.add(0, "Все категории");
        String[] categories = new String[shopInfo.getCategories().size()];
        categories = shopInfo.getCategories().toArray(categories);
        initAlertDialog(categories);
        mToolbar.getMenu().getItem(0).setEnabled(true);
    }

    @Override
    public void setCategoryName(String category) {
        if (category == null) {
            mCurrentCategoryTextView.setText(R.string.default_category);
        } else {
            mCurrentCategoryTextView.setText(category);
        }
    }

    @Override
    public void setNumItems(long numItems) {
        mNumItemsTextView.setText(getString(R.string.num_items_text, numItems));
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
}
