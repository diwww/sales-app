package ru.gcsales.app.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;

import ru.gcsales.app.R;

/**
 * Base activity class.
 *
 * @author Maxim Surovtsev
 * Created on 8/29/18
 */
public abstract class BaseActivity extends MvpAppCompatActivity {

    Toolbar mToolbar;

    /**
     * Sets up a toolbar.
     * Every child activity must have a toolbar with an id of R.id.toolbar
     * in its layout.
     */
    public void setToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }
}
