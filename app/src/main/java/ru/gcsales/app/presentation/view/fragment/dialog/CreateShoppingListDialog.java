package ru.gcsales.app.presentation.view.fragment.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import ru.gcsales.app.R;

/**
 * Create shopping list dialog class.
 * <p>
 * This dialog has an edit text to enter shopping list name and a submit button.
 *
 * @author Maxim Surovtsev
 * Created on 8/25/18
 */
public class CreateShoppingListDialog extends DialogFragment {

    public static final String EXTRA_NAME = "ru.gcsales.app.EXTRA_NAME";

    private EditText mNameEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create_shopping_list, null);
        mNameEditText = view.findViewById(R.id.edit_text_name);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.new_shopping_list_text)
                .setView(view)
                .setPositiveButton(R.string.ok_button_text, (d, w) -> {
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_NAME, mNameEditText.getText().toString());
                    getTargetFragment()
                            .onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                })
                .create();

        // Show keyboard when dialog is opened
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        // Disable OK button by default
        dialog.setOnShowListener(dialog1 -> {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
        });
        // Enable OK button if edit text is not empty
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
                }
            }
        });

        // Submit on enter key press
        mNameEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE && v.getText().length() > 0) {
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
                return true;
            }
            return false;
        });


        return dialog;
    }

    /**
     * Creates a new fragment instance.
     *
     * @return new fragment instance
     */
    public static CreateShoppingListDialog newInstance() {
        return new CreateShoppingListDialog();
    }
}
