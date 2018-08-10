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
import ru.gcsales.app.presentation.ui.activity.ShoppingListActivity;

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
        ShoppingListPreview preview = mShoppingListPreviews.get(position);
        holder.bind(preview);
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

    public void addData(ShoppingListPreview preview) {
        mShoppingListPreviews.add(preview);
        notifyDataSetChanged();
    }

    public static class ShoppingListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name) TextView mNameTextView;

        public ShoppingListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ShoppingListPreview preview) {
            final Context context = itemView.getContext();
            mNameTextView.setText(preview.getName());
            itemView.setOnClickListener(v -> context.startActivity(ShoppingListActivity.newIntent(context, preview.getId())));
        }
    }
}
