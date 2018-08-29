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
import ru.gcsales.app.presentation.presenter.ItemsPresenter;
import ru.gcsales.app.presentation.view.ItemsView;
import ru.gcsales.app.presentation.view.ShowOrHideProgress;
import ru.gcsales.app.presentation.view.activity.ShoppingListActivity;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter;


/**
 * Items fragment.
 */
public class ItemsFragment extends MvpAppCompatFragment implements ItemsView, ItemsAdapter.OnButtonClickListener {

    private static final String EXTRA_SHOP_ID = "EXTRA_SHOP_ID";
    private static final String EXTRA_CATEGORY = "EXTRA_CATEGORY";

    @InjectPresenter
    ItemsPresenter mItemsPresenter;

    @BindView(R.id.recycler_view_products) RecyclerView mRecyclerView;

    private ItemsAdapter mItemsAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private List<ShoppingListViewModel> mShoppingListViewModels;
    private ShowOrHideProgress mShowOrHideProgress;

    @ProvidePresenter
    ItemsPresenter provideProductListPresenter() {
        long shopId = getArguments().getLong(EXTRA_SHOP_ID, 0);
        String category = getArguments().getString(EXTRA_CATEGORY);
        return new ItemsPresenter(shopId, category);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mShowOrHideProgress = (ShowOrHideProgress) context;
    }

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
    public void showInitialProgress() {
        mShowOrHideProgress.showProgress();
    }

    @Override
    public void hideInitialProgress() {
        mShowOrHideProgress.hideProgress();
    }

    @Override
    public void showPageProgress() {
        mItemsAdapter.showProgress();
    }

    @Override
    public void hidePageProgress() {
        mItemsAdapter.hideProgress();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showItemAdded(long shoppingListId, String shoppingListName) {
        Snackbar.make(mRecyclerView, getString(R.string.item_added_text, shoppingListName), Snackbar.LENGTH_SHORT)
                .setAction(R.string.open_text, v ->
                        startActivity(ShoppingListActivity.newIntent(getActivity(),
                                shoppingListId, shoppingListName))
                )
                .show();
    }

    @Override
    public void addItems(List<ItemViewModel> items) {
        mItemsAdapter.addData(items);
    }

    @Override
    public void setItems(List<ItemViewModel> items) {
        mItemsAdapter.setData(items);
    }

    @Override
    public void clearProducts() {
        mItemsAdapter.clear();
    }

    @Override
    public void setShoppingLists(List<ShoppingListViewModel> shoppingListViewModels) {
        mShoppingListViewModels = shoppingListViewModels;
    }

    @Override
    public void onButtonClicked(ItemViewModel itemViewModel) {
        showAddDialog(itemViewModel);
    }

    private void showAddDialog(ItemViewModel itemViewModel) {
        ArrayAdapter<ShoppingListViewModel> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.item_shopping_list_dialog, mShoppingListViewModels);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.choose_shopping_list)
                .setAdapter(adapter, (dialog, which) -> {
                    mItemsPresenter.addItem(itemViewModel, adapter.getItem(which));
                });

        builder.create().show();
    }

    private void setOnScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItems = mLinearLayoutManager.getItemCount();
                int lastVisibleItemIndex = mLinearLayoutManager.findLastVisibleItemPosition();
                mItemsPresenter.onScrolled(totalItems, lastVisibleItemIndex);
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
