package ru.gcsales.app.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.presentation.view.activity.ProductsActivity;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ShopViewHolder> {

    private List<Shop> mShops = new ArrayList<>();

    public ShopsAdapter() {
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Shop shop = mShops.get(position);
        holder.bind(shop);
    }

    @Override
    public int getItemCount() {
        return mShops.size();
    }

    public void setData(List<Shop> data) {
        mShops = data;
        notifyDataSetChanged();
    }

    // TODO: diffutils
    // TODO: add shops to list method

    public static class ShopViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_shop_logo) ImageView mShopLogoImageView;
        @BindView(R.id.text_shop_name) TextView mShopNameTextView;

        public ShopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Shop shopModel) {
            final Context context = itemView.getContext();
            mShopNameTextView.setText(shopModel.getName());
            // Download image from url into image view
            // TODO: image placeholder
            Glide.with(context)
                    .load(shopModel.getImageUrl())
                    .into(mShopLogoImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start a new activity
                    context.startActivity(ProductsActivity.newIntent(context, shopModel.getId(), shopModel.getName()));
                }
            });
        }
    }
}
