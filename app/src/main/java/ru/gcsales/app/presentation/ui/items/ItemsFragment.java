package ru.gcsales.app.presentation.ui.items;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.leodroidcoder.genericadapter.OnEntityClickListener;

import java.util.List;

import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.presentation.presenter.ItemsPresenter;
import ru.gcsales.app.presentation.ui.base.BaseFragment;

import static ru.gcsales.app.presentation.Constants.EXTRA_SHOP_ID;


/**
 * Items fragment
 *
 * @author Maxim Surovtsev
 * @since 05/01/2019
 */
public class ItemsFragment extends BaseFragment implements ItemsView, OnEntityClickListener<Item> {

    @InjectPresenter
    ItemsPresenter mItemsPresenter;
    private ItemsAdapter mItemsAdapter;

    @ProvidePresenter
    ItemsPresenter provideItemsPresenter() {
        return new ItemsPresenter(getArguments().getLong(EXTRA_SHOP_ID));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mItemsAdapter = new ItemsAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mItemsAdapter);
    }

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

    @Override
    public void showItems(List<Item> items) {
        mItemsAdapter.setItems(items);
    }

    @Override
    public void showMoreItems(List<Item> items) {
        mItemsAdapter.addAll(items);
    }

    @Override
    public void onItemClicked(Item item) {

    }

    /**
     * Gets new fragment instance.
     *
     * @param shopId id of the shop
     * @return new fragment instance
     */
    public static ItemsFragment newInstance(long shopId) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_SHOP_ID, shopId);
        ItemsFragment fragment = new ItemsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
