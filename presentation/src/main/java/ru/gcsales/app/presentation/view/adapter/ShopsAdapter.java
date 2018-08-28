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
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.ShopViewModel;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ShopViewHolder> {

    private List<ShopViewModel> mShops = new ArrayList<>();
    private OnItemClickListener mClickListener;

    public ShopsAdapter(OnItemClickListener clickListener) {
        mClickListener = clickListener;
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
        ShopViewModel shopViewModel = mShops.get(position);
        holder.bind(shopViewModel, mClickListener);
    }

    @Override
    public int getItemCount() {
        return mShops.size();
    }

    public void setData(List<ShopViewModel> data) {
        mShops = data;
        notifyDataSetChanged();
    }

    public static class ShopViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_shop_logo) ImageView mShopLogoImageView;
        @BindView(R.id.text_shop_name) TextView mShopNameTextView;

        public ShopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ShopViewModel shopViewModel, OnItemClickListener listener) {
            final Context context = itemView.getContext();
            mShopNameTextView.setText(shopViewModel.getName());
            // Download image from url into image view
            Glide.with(context)
                    .setDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_shop_placeholder_24dp))
                    .load(shopViewModel.getImageUrl())
                    .into(mShopLogoImageView);
            itemView.setOnClickListener(v -> listener.onClick(shopViewModel));
        }
    }

    public interface OnItemClickListener {
        void onClick(ShopViewModel model);
    }
}
