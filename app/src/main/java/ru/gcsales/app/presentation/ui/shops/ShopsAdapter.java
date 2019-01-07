package ru.gcsales.app.presentation.ui.shops;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.leodroidcoder.genericadapter.BaseViewHolder;
import com.leodroidcoder.genericadapter.GenericRecyclerViewAdapter;
import com.leodroidcoder.genericadapter.OnEntityClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.Utils;

/**
 * Shops recycler view adapter
 *
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
public class ShopsAdapter extends GenericRecyclerViewAdapter<Shop, OnEntityClickListener<Shop>, ShopsAdapter.ShopViewHolder> {

    public ShopsAdapter(Context context, OnEntityClickListener<Shop> listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopViewHolder(inflate(R.layout.item_shop, parent), getListener());
    }

    /**
     * Shop view holder
     */
    public static class ShopViewHolder extends BaseViewHolder<Shop, OnEntityClickListener<Shop>> {

        @BindView(R.id.image) ImageView mImageView;

        public ShopViewHolder(View itemView, OnEntityClickListener<Shop> listener) {
            super(itemView, listener);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(Shop item) {
            Utils.setGlideImage(itemView.getContext(), item.getImageUrl(), R.drawable.ic_shop_placeholder_24dp, mImageView);
            if (getListener() != null) {
                itemView.setOnClickListener(v -> getListener().onItemClicked(item));
            }
        }
    }
}
