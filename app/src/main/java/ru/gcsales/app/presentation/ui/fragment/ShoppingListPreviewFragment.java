package ru.gcsales.app.presentation.ui.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.ShoppingList;
import ru.gcsales.app.presentation.mvp.presenter.ShoppingListPreviewPresenter;
import ru.gcsales.app.presentation.mvp.view.ShoppingListPreviewView;
import ru.gcsales.app.presentation.ui.activity.ShoppingListActivity;
import ru.gcsales.app.presentation.ui.adapter.ShoppingListPreviewsAdapter;
import ru.gcsales.app.presentation.ui.adapter.ShoppingListPreviewsAdapter.*;


public class ShoppingListPreviewFragment extends MvpAppCompatFragment
        implements ShoppingListPreviewView, OnItemClickListener, OnItemLongClickListener {

    @InjectPresenter
    ShoppingListPreviewPresenter mShoppingListPreviewPresenter;

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.recycler_view_shopping_list_previews) RecyclerView mRecyclerView;

    ShoppingListPreviewsAdapter mShoppingListPreviewsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shopping_list_preview, container, false);
        ButterKnife.bind(this, root);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mShoppingListPreviewsAdapter = new ShoppingListPreviewsAdapter(this, this);
        mRecyclerView.setAdapter(mShoppingListPreviewsAdapter);

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mShoppingListPreviewPresenter.loadData();
    }

    @Override
    public void setData(List<ShoppingList> data) {
        mShoppingListPreviewsAdapter.setData(data);
    }

    @Override
    public void addItem(ShoppingList data) {
        mShoppingListPreviewsAdapter.addItem(data);
    }

    @Override
    public void removeItem(ShoppingList item) {
        mShoppingListPreviewsAdapter.removeItem(item);
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
    public void onClick(ShoppingList preview) {
        startActivity(ShoppingListActivity.newIntent(getActivity(), preview.getId()));
    }

    @Override
    public void onLongClick(ShoppingList preview) {
        showRemoveDialog(preview);
    }

    private void showAddDialog() {
        Context context = getActivity();

        EditText editText = new EditText(context);
        editText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        editText.setHint(R.string.shopping_list_name_hint);
        editText.setMaxLines(1);

        FrameLayout frameLayout = new FrameLayout(context);
        // Set 16dp padding
        float scale = getResources().getDisplayMetrics().density;
        int padding16dp = (int) (16 * scale + 0.5f);
        frameLayout.setPadding(padding16dp, padding16dp, padding16dp, padding16dp);
        // Add editText to layout
        frameLayout.addView(editText);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.new_shopping_list_text)
                .setView(frameLayout)
                .setPositiveButton(R.string.ok_button_text, (d, w) -> {
                    mShoppingListPreviewPresenter.addShoppingList(editText.getText().toString());
                })
                .create();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
                }
            }
        });

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
    }

    private void showRemoveDialog(ShoppingList preview) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.delete_shopping_list_prompt)
                .setNegativeButton(R.string.cancel_button_text, null)
                .setPositiveButton(R.string.delete_button_text, (d, w) -> {
                    mShoppingListPreviewPresenter.removeShoppingList(preview);
                })
                .create();
        dialog.show();
    }

    public void addShoppingList() {
        showAddDialog();
    }

    /**
     * Creates a new fragment instance.
     *
     * @param bundle args to pass to a fragment
     * @return new fragment instance
     */
    public static ShoppingListPreviewFragment newInstance(Bundle bundle) {
        ShoppingListPreviewFragment shoppingListPreviewFragment = new ShoppingListPreviewFragment();
        shoppingListPreviewFragment.setArguments(bundle);
        return shoppingListPreviewFragment;
    }
}
