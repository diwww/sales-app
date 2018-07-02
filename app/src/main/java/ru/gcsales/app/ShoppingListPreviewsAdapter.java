package ru.gcsales.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListPreviewsAdapter extends RecyclerView.Adapter<ShoppingListPreviewsAdapter.ShoppingListViewHolder> {

    private List<ShoppingListPreview> mShoppingListPreviews = new ArrayList<>();
    private Context mContext;

    public ShoppingListPreviewsAdapter(Context context) {
        mContext = context;
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
        ShoppingListPreview shoppingListPreview = mShoppingListPreviews.get(position);
        holder.getNameTextView().setText(shoppingListPreview.getName());

        // TODO: improve

        StringBuilder itemsString = new StringBuilder();
        for (String item : shoppingListPreview.getItems()) {
            itemsString.append(item).append("\n");
        }

        StringBuilder customItemsString = new StringBuilder();
        for (String customItem : shoppingListPreview.getCustomItems()) {
            customItemsString.append(customItem).append("\n");
        }

        holder.getItemsTextView().setText(itemsString);
        holder.getCustomItemsTextView().setText(customItemsString);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: open shopping list activity
            }
        });
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

    public static class ShoppingListViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;
        private TextView mItemsTextView;
        private TextView mCustomItemsTextView;

        public ShoppingListViewHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.text_name);
            mItemsTextView = itemView.findViewById(R.id.text_items);
            mCustomItemsTextView = itemView.findViewById(R.id.text_custom_items);
        }

        public TextView getNameTextView() {
            return mNameTextView;
        }

        public TextView getItemsTextView() {
            return mItemsTextView;
        }

        public TextView getCustomItemsTextView() {
            return mCustomItemsTextView;
        }
    }
}
