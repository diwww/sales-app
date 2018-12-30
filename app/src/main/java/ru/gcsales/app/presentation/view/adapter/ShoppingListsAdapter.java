package ru.gcsales.app.presentation.view.adapter;

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
import ru.gcsales.app.presentation.model.ShoppingListViewModel;

public class ShoppingListsAdapter extends RecyclerView.Adapter<ShoppingListsAdapter.ShoppingListViewHolder> {

    private List<ShoppingListViewModel> mShoppingListViewModels = new ArrayList<>();
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
        ShoppingListViewModel model = mShoppingListViewModels.get(position);
        holder.bind(model, mClickListener, mLongClickListener);
    }

    @Override
    public int getItemCount() {
        return mShoppingListViewModels.size();
    }

    public void setData(List<ShoppingListViewModel> data) {
        mShoppingListViewModels.clear();
        mShoppingListViewModels.addAll(data);
        notifyDataSetChanged();
    }

    public void addItem(ShoppingListViewModel model) {
        int index = mShoppingListViewModels.size();
        mShoppingListViewModels.add(model);
        notifyItemInserted(index);
    }

    public void removeItem(ShoppingListViewModel model) {
        int index = mShoppingListViewModels.indexOf(model);
        mShoppingListViewModels.remove(index);
        notifyItemRemoved(index);
    }

    public static class ShoppingListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name) TextView mNameTextView;

        public ShoppingListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ShoppingListViewModel model, OnItemClickListener clickListener, OnItemLongClickListener longClickListener) {
            mNameTextView.setText(model.getName());
            itemView.setOnClickListener(v -> clickListener.onClick(model));
            itemView.setOnLongClickListener(v -> {
                longClickListener.onLongClick(model);
                return true;
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(ShoppingListViewModel model);
    }

    public interface OnItemLongClickListener {
        void onLongClick(ShoppingListViewModel model);
    }
}
