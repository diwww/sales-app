package ru.gcsales.app.presentation.ui.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.mvp.presenter.HomePresenter;
import ru.gcsales.app.presentation.mvp.view.HomeView;
import ru.gcsales.app.presentation.ui.fragment.ShoppingListPreviewFragment;
import ru.gcsales.app.presentation.ui.fragment.ViewPagerAdapter;

public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    @InjectPresenter
    HomePresenter mHomePresenter;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.view_pager) ViewPager mViewPager;
    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    @BindView(R.id.fab) FloatingActionButton mFloatingActionButton;

    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initTabs();
        mFloatingActionButton.setOnClickListener(v -> {
            ShoppingListPreviewFragment fragment = (ShoppingListPreviewFragment) mViewPagerAdapter.getRegisteredFragment(1);
            fragment.addShoppingList();
        });
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
}
