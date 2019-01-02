package ru.gcsales.app.presentation.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.CategoryViewModel;
import ru.gcsales.app.presentation.view.adapter.CategoriesAdapter;
import ru.gcsales.app.presentation.view.adapter.CategoriesAdapter.OnItemClickListener;

/**
 * Categories fragment.
 */
public class CategoriesFragment extends MvpAppCompatFragment implements OnItemClickListener {

    private static final String EXTRA_SHOP_ID = "ru.gcsales.EXTRA_SHOP_ID";


    @BindView(R.id.recycler_view_categories)
    RecyclerView mRecyclerView;

    private CategoriesAdapter mCategoriesAdapter;
    private LinearLayoutManager mLinearLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, root);

        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mCategoriesAdapter = new CategoriesAdapter(this);
        mRecyclerView.setAdapter(mCategoriesAdapter);

        return root;
    }

    @Override
    public void onClick(CategoryViewModel category) {
        ItemsFragment fragment = ItemsFragment.newInstance(getArguments().getLong(EXTRA_SHOP_ID), category.getName());

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Gets a new fragment instance
     *
     * @param shopId shop id
     * @return new fragment instance
     */
    public static CategoriesFragment newInstance(long shopId) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_SHOP_ID, shopId);

        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}
