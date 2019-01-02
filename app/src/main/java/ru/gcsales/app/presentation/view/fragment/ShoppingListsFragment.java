package ru.gcsales.app.presentation.view.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;
import ru.gcsales.app.presentation.view.activity.ShoppingListActivity;
import ru.gcsales.app.presentation.view.adapter.ShoppingListsAdapter;
import ru.gcsales.app.presentation.view.adapter.ShoppingListsAdapter.OnItemClickListener;
import ru.gcsales.app.presentation.view.adapter.ShoppingListsAdapter.OnItemLongClickListener;


public class ShoppingListsFragment extends MvpAppCompatFragment
        implements  OnItemClickListener, OnItemLongClickListener {

    public static final int REQUEST_NAME = 1;

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.recycler_view_shopping_list_previews) RecyclerView mRecyclerView;
    @BindView(R.id.text_stub) TextView mStubTextView;

    ShoppingListsAdapter mShoppingListsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shopping_lists, container, false);
        ButterKnife.bind(this, root);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mShoppingListsAdapter = new ShoppingListsAdapter(this, this);
        mRecyclerView.setAdapter(mShoppingListsAdapter);

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_NAME) {
//            String name = data.getStringExtra(CreateShoppingListDialog.EXTRA_NAME);
//            mShoppingListsPresenter.addShoppingList(name);
        }
    }

    @Override
    public void onClick(ShoppingListViewModel model) {
        startActivity(ShoppingListActivity.newIntent(getActivity(), model.getId(), model.getName()));
    }

    @Override
    public void onLongClick(ShoppingListViewModel model) {
        // Build and show "remove confirmation" dialog
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.delete_shopping_list_prompt)
                .setNegativeButton(R.string.cancel_button_text, null)
                .setPositiveButton(R.string.delete_button_text, (d, w) -> {
//                    mShoppingListsPresenter.removeShoppingList(model);
                })
                .create();
        dialog.show();
    }

    /**
     * Creates a new fragment instance.
     *
     * @param bundle args to pass to a fragment
     * @return new fragment instance
     */
    public static ShoppingListsFragment newInstance(Bundle bundle) {
        ShoppingListsFragment shoppingListPreviewFragment = new ShoppingListsFragment();
        shoppingListPreviewFragment.setArguments(bundle);
        return shoppingListPreviewFragment;
    }
}
