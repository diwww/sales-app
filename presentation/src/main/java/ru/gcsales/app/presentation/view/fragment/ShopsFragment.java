package ru.gcsales.app.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.ShopViewModel;
import ru.gcsales.app.presentation.presenter.ShopsPresenter;
import ru.gcsales.app.presentation.view.ShopsView;
import ru.gcsales.app.presentation.view.activity.CategoriesActivity;
import ru.gcsales.app.presentation.view.adapter.ShopsAdapter;
import ru.gcsales.app.presentation.view.adapter.ShopsAdapter.OnItemClickListener;

/**
 * Fragment for displaying available shops.
 */
public class ShopsFragment extends MvpAppCompatFragment implements ShopsView, OnItemClickListener {

    private static final int SPAN_COUNT = 2;

    @InjectPresenter
    ShopsPresenter mShopsPresenter;

    @BindView(R.id.recycler_view_shops) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    ShopsAdapter mShopsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shops, container, false);
        ButterKnife.bind(this, root);

        mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(), SPAN_COUNT));
        mShopsAdapter = new ShopsAdapter(this);
        mRecyclerView.setAdapter(mShopsAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mShopsPresenter.loadShops();
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
    public void setShops(List<ShopViewModel> shopViewModels) {
        mShopsAdapter.setData(shopViewModels);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(ShopViewModel model) {
        startActivity(CategoriesActivity.newIntent(getActivity(), model.getId(), model.getName()));
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
