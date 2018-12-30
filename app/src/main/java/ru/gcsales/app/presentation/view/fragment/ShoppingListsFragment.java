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
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.ShoppingListViewModel;
import ru.gcsales.app.presentation.presenter.ShoppingListsPresenter;
import ru.gcsales.app.presentation.view.ShoppingListsView;
import ru.gcsales.app.presentation.view.activity.ShoppingListActivity;
import ru.gcsales.app.presentation.view.adapter.ShoppingListsAdapter;
import ru.gcsales.app.presentation.view.adapter.ShoppingListsAdapter.*;
import ru.gcsales.app.presentation.view.fragment.dialog.CreateShoppingListDialog;


public class ShoppingListsFragment extends MvpAppCompatFragment
        implements ShoppingListsView, OnItemClickListener, OnItemLongClickListener {

    public static final int REQUEST_NAME = 1;
    @InjectPresenter
    ShoppingListsPresenter mShoppingListsPresenter;

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
            String name = data.getStringExtra(CreateShoppingListDialog.EXTRA_NAME);
            mShoppingListsPresenter.addShoppingList(name);
        }
    }

    @Override
    public void setData(List<ShoppingListViewModel> data) {
        if (data.size() > 0) { // Set actual data
            mShoppingListsAdapter.setData(data);
            mStubTextView.setVisibility(View.INVISIBLE);
        } else { // Show empty data stub
            mStubTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addItem(ShoppingListViewModel item) {
        mShoppingListsAdapter.addItem(item);
        // Hide empty data stub
        mStubTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void removeItem(ShoppingListViewModel item) {
        mShoppingListsAdapter.removeItem(item);
        if (mShoppingListsAdapter.getItemCount() == 0) {
            // Show empty data stub
            mStubTextView.setVisibility(View.VISIBLE);
        }
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
    public void showError(String error) {
        Toast.makeText(this.getActivity(), error, Toast.LENGTH_SHORT).show();
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
                    mShoppingListsPresenter.removeShoppingList(model);
                })
                .create();
        dialog.show();
    }

    /**
     * Invokes "create shopping list" dialog
     */
    public void createShoppingList() {
        // Show "create new shopping list" dialog
        CreateShoppingListDialog fragment = CreateShoppingListDialog.newInstance();
        fragment.setTargetFragment(this, REQUEST_NAME);
        fragment.show(getFragmentManager(), "add-dialog");
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
