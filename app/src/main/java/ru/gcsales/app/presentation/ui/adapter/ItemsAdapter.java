package ru.gcsales.app.presentation.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.CustomItem;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.ProductItem;

public class ItemsAdapter extends RecyclerView.Adapter {

    public enum ActionButtonIcon {ADD, REMOVE}

    private ActionButtonIcon mActionButtonIcon;
    private List<Item> mItems = new ArrayList<>();

    public ItemsAdapter(ActionButtonIcon actionButtonIcon) {
        mActionButtonIcon = actionButtonIcon;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Item.PRODUCT_TYPE:
                return new ProductViewHolder(inflater.inflate(R.layout.item_product, parent, false));
            case Item.CUSTOM_ITEM_TYPE:
                return new CustomItemViewHolder(inflater.inflate(R.layout.item_custom, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item item = mItems.get(position);
        switch (item.getType()) {
            case Item.PRODUCT_TYPE:
                ((ProductViewHolder) holder).bind((ProductItem) item, mActionButtonIcon);
                break;
            case Item.CUSTOM_ITEM_TYPE:
                ((CustomItemViewHolder) holder).bind((CustomItem) item);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    public void setData(List<? extends Item> data) {
        mItems.clear();
        mItems.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<? extends Item> data) {
        mItems.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image) ImageView mImageView;
        @BindView(R.id.text_name) TextView mNameTextView;
        @BindView(R.id.text_category) TextView mCategoryTextView;
        @BindView(R.id.text_old_price) TextView mOldPriceTextView;
        @BindView(R.id.text_new_price) TextView mNewPriceTextView;
        @BindView(R.id.button_action) ImageButton mActionButton;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ProductItem productItem, ActionButtonIcon actionButtonIcon) {
            final Context context = itemView.getContext();
            mNameTextView.setText(productItem.getName());
            mCategoryTextView.setText(productItem.getCategory());
            // Crossed out text
            mOldPriceTextView.setPaintFlags(mOldPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mOldPriceTextView.setText(String.format(Locale.getDefault(), "%.2f", productItem.getOldPrice()));
            mNewPriceTextView.setText(String.format(Locale.getDefault(), "%.2f", productItem.getNewPrice()));
            Glide.with(context)
                    .load(productItem.getImageUrl())
                    .into(mImageView);

            switch (actionButtonIcon) {
                case ADD:
                    mActionButton.setImageResource(R.drawable.ic_add_black_24dp);
                    break;
                case REMOVE:
                    mActionButton.setImageResource(R.drawable.ic_remove_black_24dp);
                    break;
            }
            mActionButton.setOnClickListener(v -> {
                // TODO
            });
        }
    }

    public static class CustomItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name) TextView mNameTextView;

        public CustomItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CustomItem customItem) {
            mNameTextView.setText(customItem.getName());
        }
    }
}
