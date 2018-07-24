package ru.gcsales.app.presentation.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import ru.gcsales.app.R;
import ru.gcsales.app.presentation.ui.adapter.ShoppingListPreviewsAdapter;


public class ShoppingListPreviewsFragment extends Fragment {

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
    }

    @Override
    public void onPause() {
        super.onPause();
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
