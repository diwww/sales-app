package ru.gcsales.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;


public class ShoppingListPreviewsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ShoppingListPreviewsAdapter mShoppingListPreviewsAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private DividerItemDecoration mDividerItemDecoration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shopping_list_previews, container, false);

        mRecyclerView = root.findViewById(R.id.recycler_view_shopping_lists);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mShoppingListPreviewsAdapter = new ShoppingListPreviewsAdapter(getActivity());
        mRecyclerView.setAdapter(mShoppingListPreviewsAdapter);

        mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        // FIXME: remove
        ShoppingListPreview preview =
                new ShoppingListPreview(1, "Завтрак",
                        Arrays.asList("Мясо 3кг, Мираторг",
                                "Яйца 10 шт.", "Мясо 3кг, Мираторг",
                                "Яйца 10 шт.", "Мясо 3кг, Мираторг",
                                "Яйца 10 шт.", "Мясо 3кг, Мираторг",
                                "Яйца 10 шт.", "Мясо 3кг, Мираторг",
                                "Мясо 3кг, Мираторг",
                                "Мясо 3кг, Мираторг"),
                        Arrays.asList("Хлеб", "Молоко", "Вода", "Чай"));
        mShoppingListPreviewsAdapter.setData(Arrays.asList(preview, preview, preview, preview));

        return root;
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
