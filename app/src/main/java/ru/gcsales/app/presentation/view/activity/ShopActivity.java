package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.ui.base.BaseActivity;
import ru.gcsales.app.presentation.view.fragment.CategoriesFragment;
import ru.gcsales.app.presentation.view.fragment.ItemsFragment;

/**
 * Shop activity.
 * This activity displays two fragments
 * <ol>
 * <li>{@link CategoriesFragment}</li>
 * <li>{@link ItemsFragment}</li>
 * </ol>
 */
public class ShopActivity extends BaseActivity {

    private static final String EXTRA_SHOP_ID = "ru.gcsales.EXTRA_SHOP_ID";
    private static final String EXTRA_SHOP_NAME = "ru.gcsales.EXTRA_SHOP_NAME";

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        setToolbar(true);
        setTitle(getIntent().getStringExtra(EXTRA_SHOP_NAME));
        setInitialFragment();
    }

    private void setInitialFragment() {
        CategoriesFragment fragment = CategoriesFragment
                .newInstance(getIntent().getLongExtra(EXTRA_SHOP_ID, 0));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    /**
     * Creates a new intent.
     *
     * @param context  context of caller activity
     * @param shopId   shop id
     * @param shopName shop name
     * @return new intent instance
     */
    public static Intent newIntent(Context context, long shopId, String shopName) {
        Intent intent = new Intent(context, ShopActivity.class);
        intent.putExtra(EXTRA_SHOP_ID, shopId);
        intent.putExtra(EXTRA_SHOP_NAME, shopName);
        return intent;
    }
}
