package ru.gcsales.app.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.ItemViewModel;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;
import ru.gcsales.app.presentation.view.activity.ShoppingListActivity;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter;


/**
 * Items fragment.
 */
public class ItemsFragment extends MvpAppCompatFragment implements ItemsAdapter.OnButtonClickListener {

    private static final String EXTRA_SHOP_ID = "EXTRA_SHOP_ID";
    private static final String EXTRA_CATEGORY = "EXTRA_CATEGORY";

    @BindView(R.id.recycler_view_products) RecyclerView mRecyclerView;

    private ItemsAdapter mItemsAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private List<ShoppingListViewModel> mShoppingListViewModels;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_items, container, false);
        ButterKnife.bind(this, root);

        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mItemsAdapter = new ItemsAdapter(this, R.drawable.ic_add_black_24dp);
        mRecyclerView.setAdapter(mItemsAdapter);
        setOnScrollListener();

        return root;
    }

    @Override
    public void onButtonClicked(ItemViewModel itemViewModel) {
        // TODO
    }

    private void setOnScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItems = mLinearLayoutManager.getItemCount();
                int lastVisibleItemIndex = mLinearLayoutManager.findLastVisibleItemPosition();
//                mItemsPresenter.onScrolled(totalItems, lastVisibleItemIndex);
            }
        });
    }

    /**
     * Gets new fragment instance.
     *
     * @param shopId   shop id
     * @param category category name
     * @return new fragment instance
     */
    public static ItemsFragment newInstance(long shopId, String category) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_SHOP_ID, shopId);
        bundle.putString(EXTRA_CATEGORY, category);

        ItemsFragment fragment = new ItemsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}
