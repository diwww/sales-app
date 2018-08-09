package ru.gcsales.app.presentation.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.ShoppingListPreview;
import ru.gcsales.app.presentation.mvp.presenter.ShoppingListPreviewPresenter;
import ru.gcsales.app.presentation.mvp.view.ShoppingListPreviewView;
import ru.gcsales.app.presentation.ui.adapter.ShoppingListPreviewsAdapter;


public class ShoppingListPreviewFragment extends MvpAppCompatFragment implements ShoppingListPreviewView {

    @InjectPresenter
    ShoppingListPreviewPresenter mShoppingListPreviewPresenter;

    private RecyclerView mRecyclerView;
    private ShoppingListPreviewsAdapter mShoppingListPreviewsAdapter;
    private ProgressBar mProgressBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shopping_list_preview, container, false);

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
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mShoppingListPreviewPresenter.loadData();
    }

    @Override
    public void setData(List<ShoppingListPreview> data) {
        mShoppingListPreviewsAdapter.setData(data);
    }

    /**
     * Creates a new fragment instance.
     *
     * @param bundle args to pass to a fragment
     * @return new fragment instance
     */
    public static ShoppingListPreviewFragment newInstance(Bundle bundle) {
        ShoppingListPreviewFragment shoppingListPreviewFragment = new ShoppingListPreviewFragment();
        shoppingListPreviewFragment.setArguments(bundle);
        return shoppingListPreviewFragment;
    }
}
