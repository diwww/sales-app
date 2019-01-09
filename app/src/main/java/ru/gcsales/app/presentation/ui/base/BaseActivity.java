package ru.gcsales.app.presentation.ui.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;

/**
 * Base activity with toolbar and fragment container
 *
 * @author Maxim Surovtsev
 * @since 02/01/2019
 */
public abstract class BaseActivity extends MvpAppCompatActivity {

    @BindView(R.id.toolbar) protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setToolbar();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, provideFragment(), null)
                    .commit();
        }
    }

    /**
     * Provides a fragment which will be attached to this activity
     *
     * @return {@link BaseFragment} descendant instance
     */
    protected abstract BaseFragment provideFragment();

    /**
     * Sets up a toolbar
     */
    protected abstract void setToolbar();
}
