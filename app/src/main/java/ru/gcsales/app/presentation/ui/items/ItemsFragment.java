package ru.gcsales.app.presentation.ui.items;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.ui.base.BaseFragment;

import static ru.gcsales.app.presentation.Constants.EXTRA_CATEGORY;
import static ru.gcsales.app.presentation.Constants.EXTRA_SHOP;


/**
 * Items fragment.
 */
public class ItemsFragment extends BaseFragment {


    private void setOnScrollListener() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItems = layoutManager.getItemCount();
                int lastVisibleItemIndex = layoutManager.findLastVisibleItemPosition();
//                mItemsPresenter.onScrolled(totalItems, lastVisibleItemIndex);
            }
        });
    }

    /**
     * Gets new fragment instance.
     *
     * @param shop     {@link Shop} model
     * @param category {@link Category} model
     * @return new fragment instance
     */
    public static ItemsFragment newInstance(Shop shop, Category category) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SHOP, shop);
        bundle.putSerializable(EXTRA_CATEGORY, category);
        ItemsFragment fragment = new ItemsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
