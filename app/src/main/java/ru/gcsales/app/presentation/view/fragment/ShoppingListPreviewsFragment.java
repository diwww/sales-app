package ru.gcsales.app.presentation.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.ShoppingListPreview;
import ru.gcsales.app.presentation.presenter.ShoppingListPreviewsPresenter;
import ru.gcsales.app.presentation.view.ShoppingListPreviewsMvpView;
import ru.gcsales.app.presentation.view.adapter.ShoppingListPreviewsAdapter;


public class ShoppingListPreviewsFragment extends Fragment implements ShoppingListPreviewsMvpView {

    private RecyclerView mRecyclerView;
    private ShoppingListPreviewsAdapter mShoppingListPreviewsAdapter;
    private ProgressBar mProgressBar;
    private ShoppingListPreviewsPresenter mShoppingListPreviewsPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mShoppingListPreviewsPresenter = new ShoppingListPreviewsPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shopping_list_previews, container, false);

        mRecyclerView = root.findViewById(R.id.recycler_view_shopping_list_previews);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mShoppingListPreviewsAdapter = new ShoppingListPreviewsAdapter(getActivity());
        mRecyclerView.setAdapter(mShoppingListPreviewsAdapter);
        mProgressBar = root.findViewById(R.id.progress_bar);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mShoppingListPreviewsPresenter.attachView(this);
        mShoppingListPreviewsPresenter.loadShoppingListPreviews();
    }

    @Override
    public void onPause() {
        super.onPause();
        mShoppingListPreviewsPresenter.detachView();
    }

    @Override
    public void showData(List<ShoppingListPreview> data) {
        mShoppingListPreviewsAdapter.setData(data);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * Creates new fragment instance
     *
     * @param bundle args to pass to a fragment
     * @return new fragment instance
     */
    public static ShoppingListPreviewsFragment newInstance(Bundle bundle) {
        ShoppingListPreviewsFragment shoppingListPreviewsFragment = new ShoppingListPreviewsFragment();
        shoppingListPreviewsFragment.setArguments(bundle);
        return shoppingListPreviewsFragment;
    }
}
