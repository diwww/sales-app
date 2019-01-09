package ru.gcsales.app.presentation.ui.items;

import android.content.Context;
import android.content.Intent;

import ru.gcsales.app.R;
import ru.gcsales.app.presentation.ui.base.BaseActivity;
import ru.gcsales.app.presentation.ui.base.BaseFragment;

import static ru.gcsales.app.presentation.Constants.EXTRA_SHOP_ID;
import static ru.gcsales.app.presentation.Constants.EXTRA_SHOP_NAME;

/**
 * Items activity
 *
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
public class ItemsActivity extends BaseActivity {

    @Override
    protected BaseFragment provideFragment() {
        long shopId = getIntent().getLongExtra(EXTRA_SHOP_ID, 0);
        return ItemsFragment.newInstance(shopId);
    }

    @Override
    protected void setToolbar() {
        String shopName = getIntent().getStringExtra(EXTRA_SHOP_NAME);
        mToolbar.setTitle(shopName);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Creates the new intent
     *
     * @param context context
     * @param shopId  id of the shop
     * @return the new intent
     */
    public static Intent newIntent(Context context, long shopId, String shopName) {
        Intent intent = new Intent(context, ItemsActivity.class);
        intent.putExtra(EXTRA_SHOP_ID, shopId);
        intent.putExtra(EXTRA_SHOP_NAME, shopName);
        return intent;
    }
}
