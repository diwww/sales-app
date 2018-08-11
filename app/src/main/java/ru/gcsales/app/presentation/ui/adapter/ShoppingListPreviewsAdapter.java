package ru.gcsales.app.presentation.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.ShoppingListPreview;

public class ShoppingListPreviewsAdapter extends RecyclerView.Adapter<ShoppingListPreviewsAdapter.ShoppingListViewHolder> {

    private List<ShoppingListPreview> mShoppingListPreviews = new ArrayList<>();
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

    public ShoppingListPreviewsAdapter(OnItemClickListener clickListener, OnItemLongClickListener longClickListener) {
        mClickListener = clickListener;
        mLongClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shopping_list_preview, parent, false);
        return new ShoppingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder holder, int position) {
        ShoppingListPreview preview = mShoppingListPreviews.get(position);
        holder.bind(preview, mClickListener, mLongClickListener);
    }

    @Override
    public int getItemCount() {
        return mShoppingListPreviews.size();
    }

    public void setData(List<ShoppingListPreview> data) {
        mShoppingListPreviews.clear();
        mShoppingListPreviews.addAll(data);
        notifyDataSetChanged();
    }

    public void addItem(ShoppingListPreview preview) {
        mShoppingListPreviews.add(preview);
        notifyDataSetChanged();
    }

    public void removeItem(ShoppingListPreview preview) {
        int i = mShoppingListPreviews.indexOf(preview);
        mShoppingListPreviews.remove(i);
        notifyItemRemoved(i);
    }

    public static class ShoppingListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name) TextView mNameTextView;

        public ShoppingListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ShoppingListPreview preview, OnItemClickListener clickListener, OnItemLongClickListener longClickListener) {
            mNameTextView.setText(preview.getName());
            itemView.setOnClickListener(v -> clickListener.onClick(preview));
            itemView.setOnLongClickListener(v -> {
                longClickListener.onLongClick(preview);
                return true;
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(ShoppingListPreview preview);
    }

    public interface OnItemLongClickListener {
        void onLongClick(ShoppingListPreview preview);
    }
}
