package ru.gcsales.app.presentation.ui.categories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.ui.base.BaseActivity;

import static ru.gcsales.app.presentation.Constants.EXTRA_SHOP;

/**
 * Categories activity
 *
 * @author Maxim Surovtsev
 * @since 03/01/2019
 */
public class CategoriesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Shop shop = (Shop) getIntent().getSerializableExtra(EXTRA_SHOP);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, CategoriesFragment.newInstance(shop), null)
                .commit();
    }

    public static Intent newIntent(Context context, Shop shop) {
        Intent intent = new Intent(context, CategoriesActivity.class);
        intent.putExtra(EXTRA_SHOP, shop);
        return intent;
    }
}
