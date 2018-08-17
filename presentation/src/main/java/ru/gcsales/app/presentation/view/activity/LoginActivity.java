package ru.gcsales.app.presentation.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

    // FIXME: temporary
    private ProgressDialog mProgressDialog;

    @BindView(R.id.button_login) Button mLoginButton;
    @BindView(R.id.button_register) Button mRegisterButton;
    @BindView(R.id.edit_text_username) EditText mUsernameEditText;
    @BindView(R.id.edit_text_password) EditText mPasswordEditText;

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

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getString(R.string.login_progress_text));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.hide();
    }

    @Override
    public void showToken(String token) {
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessLogin() {
        finish();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}
