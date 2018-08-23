package ru.gcsales.app.presentation.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.presenter.LoginPresenter;
import ru.gcsales.app.presentation.view.LoginView;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @InjectPresenter
    LoginPresenter mLoginPresenter;

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

            mLoginPresenter.login(username, password);
        });
        mRegisterButton.setOnClickListener(v -> {
            // TODO: start RegisterActivity
        });
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mLoginButton.setVisibility(View.GONE);
        mUsernameEditText.setEnabled(false);
        mPasswordEditText.setEnabled(false);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
        mLoginButton.setVisibility(View.VISIBLE);
        mUsernameEditText.setEnabled(true);
        mPasswordEditText.setEnabled(true);
    }

    @Override
    public void onSuccessLogin() {
        startActivity(HomeActivity.newIntent(this));
        finish();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}
