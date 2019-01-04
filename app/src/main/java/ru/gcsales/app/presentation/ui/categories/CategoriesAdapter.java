package ru.gcsales.app.presentation.ui.categories;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leodroidcoder.genericadapter.BaseViewHolder;
import com.leodroidcoder.genericadapter.GenericRecyclerViewAdapter;
import com.leodroidcoder.genericadapter.OnEntityClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.domain.model.Category;

/**
 * Categories recycler view adapter
 *
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
public class CategoriesAdapter extends GenericRecyclerViewAdapter<Category, OnEntityClickListener<Category>, CategoriesAdapter.CategoryViewHolder> {


    public CategoriesAdapter(Context context, OnEntityClickListener<Category> listener) {
        super(context, listener);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(inflate(R.layout.item_category, parent, false), getListener());
    }

    /**
     * Category view holder
     */
    public static class CategoryViewHolder extends BaseViewHolder<Category, OnEntityClickListener<Category>> {

        @BindView(R.id.text_name) TextView mNameTextView;

        public CategoryViewHolder(View itemView, OnEntityClickListener<Category> listener) {
            super(itemView, listener);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(Category item) {
            mNameTextView.setText(item.getName());
            if (getListener() != null) {
                itemView.setOnClickListener(v -> getListener().onItemClicked(item));
            }
        }
    }

}
