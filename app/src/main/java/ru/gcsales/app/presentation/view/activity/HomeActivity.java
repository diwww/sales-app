package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.ui.base.BaseActivity;
import ru.gcsales.app.presentation.view.fragment.ShoppingListsFragment;
import ru.gcsales.app.presentation.view.fragment.ViewPagerAdapter;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.view_pager) ViewPager mViewPager;
    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    @BindView(R.id.fab) FloatingActionButton mFloatingActionButton;

    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setToolbar(false);

        initTabs();
        mFloatingActionButton.setOnClickListener(v -> {
            ShoppingListsFragment fragment = (ShoppingListsFragment) mViewPagerAdapter.getRegisteredFragment(1);
            // TODO
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                // TODO: logout
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initTabs() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0)
                .setText(R.string.shops_tab_title)
                .setIcon(R.drawable.ic_home_white_24dp);
        mTabLayout.getTabAt(1)
                .setText(R.string.shopping_lists_tab_title)
                .setIcon(R.drawable.ic_shopping_basket_white_24dp);
        mTabLayout.addOnTabSelectedListener(new OnTabSelectedListener());
    }

    private final class OnTabSelectedListener implements TabLayout.OnTabSelectedListener {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if (tab.getPosition() == 1) {
                mFloatingActionButton.show();
            } else {
                mFloatingActionButton.hide();
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }
}
