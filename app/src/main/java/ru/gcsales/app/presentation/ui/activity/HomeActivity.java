package ru.gcsales.app.presentation.ui.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.mvp.presenter.HomePresenter;
import ru.gcsales.app.presentation.mvp.view.HomeView;
import ru.gcsales.app.presentation.ui.fragment.ViewPagerAdapter;

public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    @InjectPresenter
    HomePresenter mHomePresenter;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.view_pager) ViewPager mViewPager;
    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    @BindView(R.id.fab) FloatingActionButton mFloatingActionButton;
    ViewPagerAdapter mViewPagerAdapter;

    private ScaleAnimation mShrink;
    private ScaleAnimation mExpand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initAnimations();
        initTabs();
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

    private void initAnimations() {
        final Interpolator interpolator = new DecelerateInterpolator();
        final AnimationListener listener = new AnimationListener();
        final int duration = 150;

        mShrink = new ScaleAnimation(1f, 0.0f, 1f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mShrink.setDuration(duration);
        mShrink.setInterpolator(interpolator);
        mShrink.setAnimationListener(listener);

        mExpand = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mExpand.setDuration(duration);
        mExpand.setInterpolator(interpolator);
        mExpand.setAnimationListener(listener);
    }

    private final class OnTabSelectedListener implements TabLayout.OnTabSelectedListener {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if (tab.getPosition() == 1) {
                mFloatingActionButton.startAnimation(mExpand);
            } else {
                mFloatingActionButton.startAnimation(mShrink);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    }

    private final class AnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            if (animation == mExpand) {
                mFloatingActionButton.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (animation == mShrink) {
                mFloatingActionButton.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }
}
