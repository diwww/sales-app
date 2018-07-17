package ru.gcsales.app.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.Shop;
import ru.gcsales.app.presentation.presenter.ShopsPresenter;
import ru.gcsales.app.presentation.view.ShopsMvpView;
import ru.gcsales.app.presentation.view.adapter.ShopsAdapter;

/**
 * Fragment for displaying available shops
 */
public class ShopsFragment extends Fragment implements ShopsMvpView {

    private static final int SPAN_COUNT = 2;

    private RecyclerView mRecyclerView;
    private ShopsAdapter mShopsAdapter;
    private ProgressBar mProgressBar;
    private ShopsPresenter mShopsPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mShopsPresenter = new ShopsPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shops, container, false);

        mRecyclerView = root.findViewById(R.id.recycler_view_shops);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(), SPAN_COUNT));
        mShopsAdapter = new ShopsAdapter(getActivity());
        mRecyclerView.setAdapter(mShopsAdapter);
        mProgressBar = root.findViewById(R.id.progress_bar);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mShopsPresenter.attachView(this);
        mShopsPresenter.loadShops();
    }

    @Override
    public void onPause() {
        super.onPause();
        mShopsPresenter.detachView();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<Shop> data) {
        mShopsAdapter.setData(data);
    }

    /**
     * Creates new fragment instance
     *
     * @param bundle args to pass to a fragment
     * @return new fragment instance
     */
    public static ShopsFragment newInstance(Bundle bundle) {
        ShopsFragment shopsFragment = new ShopsFragment();
        shopsFragment.setArguments(bundle);
        return shopsFragment;
    }
}
