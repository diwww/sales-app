package ru.gcsales.app.presentation.ui.main;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import ru.gcsales.app.presentation.ui.shoppinglist.ShoppingListFragment;
import ru.gcsales.app.presentation.ui.shops.ShopsFragment;


/**
 * Viewpager adapter
 *
 * @author Maxim Surovtsev
 * @since 07/08/2018
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;

    // Sparse array to keep track of registered fragments in memory
    private SparseArray<Fragment> registeredFragments = new SparseArray<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Register the fragment when the item is instantiated
    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    // Unregister when the item is inactive
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ShopsFragment.newInstance();
            case 1:
                return ShoppingListFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    /**
     * Returns the fragment for the position.
     *
     * @param position position of the fragment
     * @return {@link Fragment} instance
     */
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
