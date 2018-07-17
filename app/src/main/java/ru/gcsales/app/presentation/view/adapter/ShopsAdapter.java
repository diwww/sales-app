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

import ru.gcsales.app.R;
import ru.gcsales.app.presentation.view.activity.ProductsActivity;
import ru.gcsales.app.presentation.model.Shop;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ShopViewHolder> {

    private List<Shop> mShops = new ArrayList<>();
    private Context mContext;

    public ShopsAdapter(Context context) {
        mContext = context;
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
        holder.getShopNameTextView().setText(shop.getName());
        // Download image from url into image view
        Glide.with(mContext).load(shop.getImageUrl())
                .into(holder.getShopLogoImageView());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: open new activity
                mContext.startActivity(ProductsActivity.newIntent(mContext, 0));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mShops.size();
    }

    public void setData(List<Shop> data) {
        mShops.clear();
        mShops.addAll(data);
        notifyDataSetChanged();
    }

    // TODO: diffutils
    // TODO: add shops to list method

    public static class ShopViewHolder extends RecyclerView.ViewHolder {

        private ImageView mShopLogoImageView;
        private TextView mShopNameTextView;

        public ShopViewHolder(View itemView) {
            super(itemView);
            mShopLogoImageView = itemView.findViewById(R.id.image_shop_logo);
            mShopNameTextView = itemView.findViewById(R.id.text_shop_name);
        }

        public ImageView getShopLogoImageView() {
            return mShopLogoImageView;
        }

        public TextView getShopNameTextView() {
            return mShopNameTextView;
        }
    }
}
