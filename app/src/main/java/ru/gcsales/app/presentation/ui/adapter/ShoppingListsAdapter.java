package ru.gcsales.app.presentation.ui.adapter;

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
import ru.gcsales.app.domain.model.ShoppingList;

public class ShoppingListsAdapter extends RecyclerView.Adapter<ShoppingListsAdapter.ShoppingListViewHolder> {

    private List<ShoppingList> mShoppingLists = new ArrayList<>();
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

    public ShoppingListsAdapter(OnItemClickListener clickListener, OnItemLongClickListener longClickListener) {
        mClickListener = clickListener;
        mLongClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shopping_list, parent, false);
        return new ShoppingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder holder, int position) {
        ShoppingList preview = mShoppingLists.get(position);
        holder.bind(preview, mClickListener, mLongClickListener);
    }

    @Override
    public int getItemCount() {
        return mShoppingLists.size();
    }

    public void setData(List<ShoppingList> data) {
        mShoppingLists.clear();
        mShoppingLists.addAll(data);
        notifyDataSetChanged();
    }

    public void addItem(ShoppingList preview) {
        mShoppingLists.add(preview);
        notifyDataSetChanged();
    }

    public void removeItem(ShoppingList preview) {
        int i = mShoppingLists.indexOf(preview);
        mShoppingLists.remove(i);
        notifyItemRemoved(i);
    }

    public static class ShoppingListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name) TextView mNameTextView;

        public ShoppingListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ShoppingList preview, OnItemClickListener clickListener, OnItemLongClickListener longClickListener) {
            mNameTextView.setText(preview.getName());
            itemView.setOnClickListener(v -> clickListener.onClick(preview));
            itemView.setOnLongClickListener(v -> {
                longClickListener.onLongClick(preview);
                return true;
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(ShoppingList preview);
    }

    public interface OnItemLongClickListener {
        void onLongClick(ShoppingList preview);
    }
}
