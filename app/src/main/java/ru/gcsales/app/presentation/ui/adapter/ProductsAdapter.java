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
import ru.gcsales.app.presentation.mvp.model.ProductModel;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<ProductModel> mProductModels = new ArrayList<>();

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final ProductModel productModel = mProductModels.get(position);
        holder.bind(productModel);
    }

    public void setData(List<ProductModel> data) {
        mProductModels.clear();
        mProductModels.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mProductModels.size();
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

        public void bind(ProductModel productModel) {
            final Context context = itemView.getContext();
            mNameTextView.setText(productModel.getName());
            mCategoryTextView.setText(productModel.getCategory());
            mOldPriceTextView.setText(String.format(Locale.getDefault(), "%.2f", productModel.getOldPrice()));
            mNewPriceTextView.setText(String.format(Locale.getDefault(), "%.2f", productModel.getNewPrice()));
            Glide.with(context)
                    .load(productModel.getImageUrl())
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
