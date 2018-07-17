package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.gcsales.app.R;
import ru.gcsales.app.presentation.presenter.LoginPresenter;
import ru.gcsales.app.presentation.view.LoginMvpView;

public class LoginActivity extends AppCompatActivity implements LoginMvpView {

    private Button mLoginButton;
    private Button mRegisterButton;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private LoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        setListeners();
        mLoginPresenter = new LoginPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLoginPresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLoginPresenter.detachView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void initViews() {
        mLoginButton = findViewById(R.id.button_login);
        mRegisterButton = findViewById(R.id.button_register);
        mUsernameEditText = findViewById(R.id.edit_text_username);
        mPasswordEditText = findViewById(R.id.edit_text_password);
    }

    private void setListeners() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                mLoginPresenter.login(username, password);
            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: start RegisterActivity
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}
