package ru.gcsales.app.presentation.ui.base;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;

/**
 * Base activity class
 *
 * @author Maxim Surovtsev
 * @since 02/01/2019
 */
public abstract class BaseActivity extends MvpAppCompatActivity implements BaseView {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.container_stub) View mStubContainer;
    @BindView(R.id.image_stub) ImageView mStubImageView;
    @BindView(R.id.text_stub) TextView mStubTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setToolbar();
    }

    @Override
    public void showProgress(boolean visible) {
        mRecyclerView.setVisibility(visible ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showStub(boolean visible) {
        mRecyclerView.setVisibility(visible ? View.GONE : View.VISIBLE);
        mStubContainer.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * Sets up a stub view
     *
     * @param image stub image resource
     * @param text  stub text resource
     */
    protected void setStubView(@DrawableRes int image, @StringRes int text) {
        mStubImageView.setImageResource(image);
        mStubTextView.setText(text);
    }

    /**
     * Sets up a toolbar
     */
    protected void setToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }
}
