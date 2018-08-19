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
import ru.gcsales.app.presentation.model.BaseItem;
import ru.gcsales.app.presentation.model.ItemViewModel;
import ru.gcsales.app.presentation.model.ProgressViewModel;

public class ItemsAdapter extends RecyclerView.Adapter {

    private List<BaseItem> mItems = new ArrayList<>();
    private OnButtonClickListener mButtonClickListener;
    private final ProgressViewModel mProgressViewModel = new ProgressViewModel();

    public ItemsAdapter(OnButtonClickListener listener) {
        mButtonClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ItemViewModel.TYPE:
                return new ItemViewHolder(inflater.inflate(R.layout.item_item, parent, false));
            case ProgressViewModel.TYPE:
                return new ProgressViewHolder(inflater.inflate(R.layout.item_progress, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseItem item = mItems.get(position);
        if (item.getType() == ItemViewModel.TYPE) {
            ((ItemViewHolder) holder).bind((ItemViewModel) item, mButtonClickListener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    public void showProgress() {
        mItems.add(mProgressViewModel);
        notifyDataSetChanged();
    }

    public void hideProgress() {
        mItems.remove(mProgressViewModel);
        notifyDataSetChanged();
    }

    public void setData(List<? extends BaseItem> data) {
        mItems.clear();
        mItems.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<? extends BaseItem> data) {
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

    /**
     * Item view holder.
     */
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

        public void bind(ItemViewModel item, OnButtonClickListener buttonClickListener) {
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

            mActionButton.setImageResource(R.drawable.ic_add_black_24dp);
            mActionButton.setOnClickListener(v -> buttonClickListener.onButtonClicked(item));
        }
    }

    /**
     * Progress bar view holder.
     */
    public static class ProgressViewHolder extends RecyclerView.ViewHolder {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnButtonClickListener {
        void onButtonClicked(ItemViewModel item);
    }
}
