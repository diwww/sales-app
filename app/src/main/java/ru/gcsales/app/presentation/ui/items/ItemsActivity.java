package ru.gcsales.app.presentation.ui.items;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.ui.base.BaseActivity;

import static ru.gcsales.app.presentation.Constants.EXTRA_CATEGORY;
import static ru.gcsales.app.presentation.Constants.EXTRA_SHOP;

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
        Shop shop = (Shop) getIntent().getSerializableExtra(EXTRA_SHOP);
        Category category = (Category) getIntent().getSerializableExtra(EXTRA_CATEGORY);
        setToolbar(shop.getName());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, ItemsFragment.newInstance(shop, category), null)
                .commit();
    }

    /**
     * Creates the new intent
     *
     * @param context  context
     * @param shop     {@link Shop} model
     * @param category {@link Category} model
     * @return the new intent
     */
    public static Intent newIntent(Context context, Shop shop, Category category) {
        Intent intent = new Intent(context, ItemsActivity.class);
        intent.putExtra(EXTRA_SHOP, shop);
        intent.putExtra(EXTRA_CATEGORY, category);
        return intent;
    }
}
