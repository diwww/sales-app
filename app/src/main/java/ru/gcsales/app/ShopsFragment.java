package ru.gcsales.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fragment for displaying available shops
 */
public class ShopsFragment extends Fragment {

    private static final int SPAN_COUNT = 2;

    private RecyclerView mRecyclerView;
    private ShopsAdapter mShopsAdapter;
    private GridLayoutManager mGridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shops, container, false);

        mRecyclerView = root.findViewById(R.id.recycler_view_shops);

        mGridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mShopsAdapter = new ShopsAdapter(getActivity());
        mRecyclerView.setAdapter(mShopsAdapter);

        // FIXME: remove
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gcsales.ru/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIRequests apiRequests = retrofit.create(APIRequests.class);
        apiRequests.getShops().enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                mShopsAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {

            }
        });

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
