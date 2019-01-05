package ru.gcsales.app.presentation.ui.items;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ru.gcsales.app.R;
import ru.gcsales.app.presentation.ui.base.BaseActivity;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String shopName = getIntent().getStringExtra(EXTRA_SHOP_NAME);
        long shopId = getIntent().getLongExtra(EXTRA_SHOP_ID, 0);
        setToolbar(shopName);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, ItemsFragment.newInstance(shopId), null)
                .commit();
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
