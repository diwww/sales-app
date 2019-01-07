package ru.gcsales.app.presentation.ui.items;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.leodroidcoder.genericadapter.BaseViewHolder;
import com.leodroidcoder.genericadapter.GenericRecyclerViewAdapter;
import com.leodroidcoder.genericadapter.OnEntityClickListener;

import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.presentation.Utils;

/**
 * Items recycler view adapter
 *
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
public class ItemsAdapter extends GenericRecyclerViewAdapter<Item, OnEntityClickListener<Item>, ItemsAdapter.ItemViewHolder> {

    public ItemsAdapter(Context context, OnEntityClickListener<Item> listener) {
        super(context, listener);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflate(R.layout.item_item, parent, false), getListener());
    }

    /**
     * Item view holder
     */
    public static class ItemViewHolder extends BaseViewHolder<Item, OnEntityClickListener<Item>> {

        @BindView(R.id.image) ImageView mImageView;
        @BindView(R.id.text_name) TextView mNameTextView;
        @BindView(R.id.text_old_price) TextView mOldPriceTextView;
        @BindView(R.id.text_new_price) TextView mNewPriceTextView;
        @BindView(R.id.text_date) TextView mDateTextView;
        @BindView(R.id.button_action) ImageButton mActionButton;

        public ItemViewHolder(View itemView, OnEntityClickListener<Item> listener) {
            super(itemView, listener);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(Item item) {
            final Context context = itemView.getContext();
            mNameTextView.setText(item.getName());
            // Crossed out text
            mOldPriceTextView.setPaintFlags(mOldPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mOldPriceTextView.setText(context.getString(R.string.price_placeholder, item.getOldPrice()));
            mNewPriceTextView.setText(context.getString(R.string.price_placeholder, item.getNewPrice()));
            Date dateTill = item.getTill();
            mDateTextView.setText(context.getString(R.string.date_till, dateTill, dateTill));
            Utils.setGlideImage(context, item.getImageUrl(), R.drawable.ic_item_placeholder_24dp, mImageView);
            if (getListener() != null) {
                mActionButton.setOnClickListener(v -> getListener().onItemClicked(item));
            }
        }
    }
}
