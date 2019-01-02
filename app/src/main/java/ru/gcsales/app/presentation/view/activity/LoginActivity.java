package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.button_login) Button mLoginButton;
    @BindView(R.id.button_register) Button mRegisterButton;
    @BindView(R.id.edit_text_username) EditText mUsernameEditText;
    @BindView(R.id.edit_text_password) EditText mPasswordEditText;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginButton.setOnClickListener(v -> {
            String username = mUsernameEditText.getText().toString();
            String password = mPasswordEditText.getText().toString();

            // TODO: perform login
        });
        mRegisterButton.setOnClickListener(v -> {
            startActivity(RegisterActivity.newIntent(this));
        });
    }

    /**
     * Creates new intent.
     *
     * @param context context
     * @return new intent instance
     */
    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}
