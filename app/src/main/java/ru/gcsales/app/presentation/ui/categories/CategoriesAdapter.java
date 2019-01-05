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

/**
 * Categories recycler view adapter
 *
 * @author Maxim Surovtsev
 * @since 04/01/2019
 */
public class CategoriesAdapter extends GenericRecyclerViewAdapter<String, OnEntityClickListener<String>, CategoriesAdapter.CategoryViewHolder> {


    public CategoriesAdapter(Context context, OnEntityClickListener<String> listener) {
        super(context, listener);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(inflate(R.layout.item_category, parent, false), getListener());
    }

    /**
     * Category view holder
     */
    public static class CategoryViewHolder extends BaseViewHolder<String, OnEntityClickListener<String>> {

        @BindView(R.id.text_name) TextView mNameTextView;

        public CategoryViewHolder(View itemView, OnEntityClickListener<String> listener) {
            super(itemView, listener);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(String item) {
            mNameTextView.setText(item);
            if (getListener() != null) {
                itemView.setOnClickListener(v -> getListener().onItemClicked(item));
            }
        }
    }

}
