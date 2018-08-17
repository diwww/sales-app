package ru.gcsales.app.presentation.view.adapter;

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
import ru.gcsales.app.domain.model.Item;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    public enum ActionButtonIcon {ADD, REMOVE}

    private ActionButtonIcon mActionButtonIcon;
    private List<Item> mItems = new ArrayList<>();
    private OnButtonClickListener mButtonClickListener;

    public ItemsAdapter(ActionButtonIcon actionButtonIcon, OnButtonClickListener listener) {
        mActionButtonIcon = actionButtonIcon;
        mButtonClickListener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ItemViewHolder(inflater.inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = mItems.get(position);
        holder.bind(item, mActionButtonIcon, mButtonClickListener);
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

    public void deleteItem(Item item) {
        mItems.remove(item);
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

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image) ImageView mImageView;
        @BindView(R.id.text_name) TextView mNameTextView;
        @BindView(R.id.text_category) TextView mCategoryTextView;
        @BindView(R.id.text_old_price) TextView mOldPriceTextView;
        @BindView(R.id.text_new_price) TextView mNewPriceTextView;
        @BindView(R.id.button_action) ImageButton mActionButton;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Item item, ActionButtonIcon actionButtonIcon,
                         OnButtonClickListener buttonClickListener) {
            final Context context = itemView.getContext();
            mNameTextView.setText(item.getName());
            mCategoryTextView.setText(item.getCategory());
            // Crossed out text
            mOldPriceTextView.setPaintFlags(mOldPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mOldPriceTextView.setText(String.format(Locale.getDefault(), "%.2f", item.getOldPrice()));
            mNewPriceTextView.setText(String.format(Locale.getDefault(), "%.2f", item.getNewPrice()));
            Glide.with(context)
                    .load(item.getImageUrl())
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
                buttonClickListener.onButtonClicked(item);
            });
        }
    }

    public interface OnButtonClickListener {
        void onButtonClicked(Item item);
    }
}
