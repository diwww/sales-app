package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import ru.gcsales.app.R;
import ru.gcsales.app.presentation.presenter.RegisterPresenter;
import ru.gcsales.app.presentation.view.RegisterView;


public class RegisterActivity extends BaseActivity implements RegisterView {

    public static final int NUM_CHARACTERS_USERNAME = 6;
    public static final int NUM_CHARACTERS_PASSWORD = 6;

    @InjectPresenter
    RegisterPresenter mRegisterPresenter;

    @BindView(R.id.edit_text_username) EditText mUsernameEditText;
    @BindView(R.id.edit_text_password) EditText mPasswordEditText;
    @BindView(R.id.button_register) Button mRegisterButton;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.text_username_num_characters) TextView mUsernameNumCharactersTextView;
    @BindView(R.id.text_password_num_characters) TextView mPasswordNumCharactersTextView;

    private boolean mSatisfyingUsername = false;
    private boolean mSatisfyingPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mUsernameNumCharactersTextView.setText(getString(R.string.num_characters, 0, NUM_CHARACTERS_USERNAME));
        mPasswordNumCharactersTextView.setText(getString(R.string.num_characters, 0, NUM_CHARACTERS_USERNAME));

        mUsernameEditText.addTextChangedListener(new MyTextWatcher(this::checkUsername));
        mPasswordEditText.addTextChangedListener(new MyTextWatcher(this::checkPassword));

        mRegisterButton.setOnClickListener(v -> {
            String username = mUsernameEditText.getText().toString();
            String password = mPasswordEditText.getText().toString();
            mRegisterPresenter.register(username, password);
        });
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRegisterButton.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
        mRegisterButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessRegister() {
        Toast.makeText(this, getString(R.string.success_registration_message), Toast.LENGTH_SHORT).show();
        finish();
    }


    private void checkUsername(Editable username) {
        mUsernameNumCharactersTextView.setText(getString(R.string.num_characters, username.length(), NUM_CHARACTERS_USERNAME));
        mSatisfyingUsername = username.length() >= NUM_CHARACTERS_USERNAME;
        tryEnableButton();
    }

    private void checkPassword(Editable password) {
        mPasswordNumCharactersTextView.setText(getString(R.string.num_characters, password.length(), NUM_CHARACTERS_PASSWORD));
        mSatisfyingPassword = password.length() >= NUM_CHARACTERS_PASSWORD;
        tryEnableButton();
    }

    private void tryEnableButton() {
        mRegisterButton.setEnabled(mSatisfyingPassword && mSatisfyingUsername);
    }

    /**
     * Text watcher which has common logic for
     * username and password edit texts
     */
    private final class MyTextWatcher implements TextWatcher {

        private Consumer<Editable> mConsumer;

        public MyTextWatcher(Consumer<Editable> consumer) {
            mConsumer = consumer;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                mConsumer.accept(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates new intent.
     *
     * @param context context
     * @return new intent instance
     */
    public static Intent newIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }
}
