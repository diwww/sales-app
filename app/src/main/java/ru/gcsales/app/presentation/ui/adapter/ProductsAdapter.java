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
import ru.gcsales.app.domain.model.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> mProducts = new ArrayList<>();

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product product = mProducts.get(position);
        holder.bind(product);
    }

    public void setData(List<Product> data) {
        mProducts.clear();
        mProducts.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<Product> data) {
        mProducts.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        mProducts.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image) ImageView mImageView;
        @BindView(R.id.text_name) TextView mNameTextView;
        @BindView(R.id.text_category) TextView mCategoryTextView;
        @BindView(R.id.text_old_price) TextView mOldPriceTextView;
        @BindView(R.id.text_new_price) TextView mNewPriceTextView;
        @BindView(R.id.button_add) ImageButton mAddButton;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            // Crossed out text
            mOldPriceTextView.setPaintFlags(mOldPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        public void bind(Product product) {
            final Context context = itemView.getContext();
            mNameTextView.setText(product.getName());
            mCategoryTextView.setText(product.getCategory());
            mOldPriceTextView.setText(String.format(Locale.getDefault(), "%.2f", product.getOldPrice()));
            mNewPriceTextView.setText(String.format(Locale.getDefault(), "%.2f", product.getNewPrice()));
            Glide.with(context)
                    .load(product.getImageUrl())
                    .into(mImageView);
            mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO
                }
            });
        }
    }
}
