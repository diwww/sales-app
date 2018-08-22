package ru.gcsales.app.presentation.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.model.CategoryViewModel;

/**
 * @author Maxim Surovtsev
 * Created on 8/20/18
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private List<CategoryViewModel> mCategories = new ArrayList<>();
    private OnItemClickListener mClickListener;

    public CategoriesAdapter(OnItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryViewModel category = mCategories.get(position);
        holder.bind(category, mClickListener);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void setData(List<CategoryViewModel> data) {
        mCategories.clear();
        mCategories.addAll(data);
        notifyDataSetChanged();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name)
        TextView mNameTextView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CategoryViewModel category, OnItemClickListener listener) {
            mNameTextView.setText(category.getName());
            itemView.setOnClickListener(v -> listener.onClick(category));
        }
    }

    public interface OnItemClickListener {
        void onClick(CategoryViewModel category);
    }
}
