package ru.gcsales.app.presentation.ui.shoppinglist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.leodroidcoder.genericadapter.BaseViewHolder;
import com.leodroidcoder.genericadapter.GenericRecyclerViewAdapter;
import com.leodroidcoder.genericadapter.OnEntityClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.ShoppingListEntry;
import ru.gcsales.app.presentation.Utils;

/**
 * Shopping list adapter
 *
 * @author Maxim Surovtsev
 * @since 07/01/2019
 */
public class ShoppingListAdapter extends GenericRecyclerViewAdapter<ShoppingListEntry, OnEntityClickListener<ShoppingListEntry>, ShoppingListAdapter.ShoppingListEntryViewHolder> {

    public ShoppingListAdapter(Context context, OnEntityClickListener<ShoppingListEntry> listener) {
        super(context, listener);
    }

    @Override
    public ShoppingListEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShoppingListEntryViewHolder(inflate(R.layout.item_shopping_list_entry, parent), getListener());
    }

    @Override
    public void setItems(List<ShoppingListEntry> items) {
        super.setItems(items);
        // FIXME: prettify this code (or maybe this data should be sent from server)
//        List<ShoppingListEntry> entries = getItems();
//        String shop = entries.get(0).setShowShop(true).getShop();
//        for (ShoppingListEntry entry : entries) {
//            if (!shop.equals(entry.getShop())) {
//                shop = entry.getShop();
//                entry.setShowShop(true);
//            }
//        }
    }

    public static class ShoppingListEntryViewHolder extends BaseViewHolder<ShoppingListEntry, OnEntityClickListener<ShoppingListEntry>> {

        @BindView(R.id.text_shop) TextView mShopTextView;
        @BindView(R.id.text_title) TextView mTitleTextView;
        @BindView(R.id.text_price) TextView mPriceTextView;
        @BindView(R.id.image) ImageView mImageView;
        @BindView(R.id.button_less) ImageButton mLessButton;
        @BindView(R.id.button_more) ImageButton mMoreButton;
        @BindView(R.id.text_quantity) TextView mQuantityTextView;

        public ShoppingListEntryViewHolder(View itemView, OnEntityClickListener<ShoppingListEntry> listener) {
            super(itemView, listener);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(ShoppingListEntry item) {
            final Context context = itemView.getContext();
            mShopTextView.setText(item.getShop());
            mShopTextView.setVisibility(item.isShowShop() ? View.VISIBLE : View.GONE);
            mTitleTextView.setText(item.getTitle());
            mPriceTextView.setText(context.getString(R.string.price_placeholder, item.getPrice()));
            mQuantityTextView.setText(context.getString(R.string.quantity_placeholder, item.getQuantity()));
            Utils.setGlideImage(context, item.getImageUrl(), R.drawable.ic_item_placeholder_24dp, mImageView);
            // TODO: listeners
        }
    }
}
