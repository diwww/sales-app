package ru.gcsales.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShoppingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
    }

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, ShoppingListActivity.class);
        // TODO: put extra id and etc...
        return intent;
    }
}
