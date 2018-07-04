package ru.gcsales.app;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> mProducts = new ArrayList<>();
    private Context mContext;

    public ProductsAdapter(Context context) {
        mContext = context;
    }

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
        holder.getNameTextView().setText(product.getName());
        holder.getCategoryTextView().setText(product.getCategory());
        holder.getOldPriceTextView()
                .setText(String.format(Locale.getDefault(), "%.2f", product.getOldPrice()));
        holder.getNewPriceTextView()
                .setText(String.format(Locale.getDefault(), "%.2f", product.getNewPrice()));
//        holder.getDiscountTextView().setText(product.getDiscount());
//        holder.getDateTextView().setText(product.getDateIn() + "\u2014" + product.getDateOut());
        // Crossed out text
        holder.getOldPriceTextView().setPaintFlags(holder.getOldPriceTextView().getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.getAddButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
                Toast.makeText(mContext, product.getId() + "", Toast.LENGTH_LONG).show();
            }
        });

        // TODO: download image from url
    }

    public void setData(List<Product> data) {
        mProducts.clear();
        mProducts.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mNameTextView;
        private TextView mCategoryTextView;
        private TextView mOldPriceTextView;
        private TextView mNewPriceTextView;
        //        private TextView mDiscountTextView;
//        private TextView mDateTextView;
        private ImageButton mAddButton;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            mNameTextView = itemView.findViewById(R.id.text_name);
            mCategoryTextView = itemView.findViewById(R.id.text_category);
            mOldPriceTextView = itemView.findViewById(R.id.text_old_price);
            mNewPriceTextView = itemView.findViewById(R.id.text_new_price);
            mAddButton = itemView.findViewById(R.id.button_add);
        }

        public ImageView getImageView() {
            return mImageView;
        }

        public TextView getNameTextView() {
            return mNameTextView;
        }

        public TextView getCategoryTextView() {
            return mCategoryTextView;
        }

        public TextView getOldPriceTextView() {
            return mOldPriceTextView;
        }

        public TextView getNewPriceTextView() {
            return mNewPriceTextView;
        }

//        public TextView getDiscountTextView() {
//            return mDiscountTextView;
//        }
//
//        public TextView getDateTextView() {
//            return mDateTextView;
//        }

        public ImageButton getAddButton() {
            return mAddButton;
        }
    }
}
