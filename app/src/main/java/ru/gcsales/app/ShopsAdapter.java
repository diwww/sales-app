package ru.gcsales.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
        // TODO: download image from url
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: open new activity
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
