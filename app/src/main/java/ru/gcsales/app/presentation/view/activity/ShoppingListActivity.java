package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.ItemViewModel;
import ru.gcsales.app.presentation.ui.base.BaseActivity;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter;
import ru.gcsales.app.presentation.view.adapter.ItemsAdapter.OnButtonClickListener;

public class ShoppingListActivity extends BaseActivity
        implements OnButtonClickListener {

    public static final String EXTRA_SHOPPING_LIST_ID = "EXTRA_SHOPPING_LIST_ID";
    public static final String EXTRA_SHOPPING_LIST_NAME = "EXTRA_SHOPPING_LIST_NAME";


    @BindView(R.id.text_total_price) TextView mTotalPriceTextView;
    @BindView(R.id.recycler_view_items) RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.text_stub) TextView mStubTextView;

    LinearLayoutManager mLinearLayoutManager;
    ItemsAdapter mItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        ButterKnife.bind(this);
        setToolbar(true);
        setTitle(getIntent().getStringExtra(EXTRA_SHOPPING_LIST_NAME));
        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mItemsAdapter = new ItemsAdapter(this, R.drawable.ic_remove_black_24dp);
        mRecyclerView.setAdapter(mItemsAdapter);
    }


    @Override
    public void onButtonClicked(ItemViewModel item) {

    }

    public static Intent newIntent(Context context, long id, String name) {
        Intent intent = new Intent(context, ShoppingListActivity.class);
        intent.putExtra(EXTRA_SHOPPING_LIST_ID, id);
        intent.putExtra(EXTRA_SHOPPING_LIST_NAME, name);
        return intent;
    }
}
