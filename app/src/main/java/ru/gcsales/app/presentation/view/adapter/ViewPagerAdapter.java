package ru.gcsales.app.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.gcsales.app.R;
import ru.gcsales.app.presentation.view.fragment.ShoppingListPreviewsFragment;
import ru.gcsales.app.presentation.view.fragment.ShopsFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;
    private Context mContext;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ShopsFragment.newInstance(null);
            case 1:
                return ShoppingListPreviewsFragment.newInstance(null);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.shops_tab_title);
            case 1:
                return mContext.getResources().getString(R.string.shopping_lists_tab_title);
            default:
                return null;
        }
    }
}
