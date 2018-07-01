package ru.gcsales.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

/**
 * Fragment for displaying available shops
 */
public class ShopsFragment extends Fragment {

    private static final int SPAN_COUNT = 2;

    private RecyclerView mRecyclerView;
    private ShopsAdapter mShopsAdapter;
    private GridLayoutManager mGridLayoutManager;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shops, container, false);

        mRecyclerView = root.findViewById(R.id.recycler_view_shops);

        mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mShopsAdapter = new ShopsAdapter(getActivity());
        mRecyclerView.setAdapter(mShopsAdapter);

        // FIXME: remove
        mShopsAdapter.setData(Arrays.asList(
                new Shop(1, "dixy", "Дикси", null),
                new Shop(1, "dixy", "Пятерочка", null),
                new Shop(1, "dixy", "Перекресток", null),
                new Shop(1, "dixy", "Перекресток", null),
                new Shop(1, "dixy", "Перекресток", null),
                new Shop(1, "dixy", "Перекресток", null),
                new Shop(1, "dixy", "Перекресток", null),
                new Shop(1, "dixy", "Перекресток", null),
                new Shop(1, "dixy", "Перекресток", null),
                new Shop(1, "dixy", "Перекресток", null),
                new Shop(1, "dixy", "Перекресток", null),
                new Shop(1, "dixy", "Перекресток", null)
        ));

        return root;
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
