package ru.gcsales.app.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.gcsales.app.R;
import ru.gcsales.app.presentation.presenter.ShoppingListPresenter;
import ru.gcsales.app.presentation.view.ShoppingListMvpView;

public class ShoppingListActivity extends AppCompatActivity implements ShoppingListMvpView {

    private ShoppingListPresenter mShoppingListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        mShoppingListPresenter = new ShoppingListPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShoppingListPresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShoppingListPresenter.detachView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, ShoppingListActivity.class);
        // TODO: put extra id and etc...
        return intent;
    }
}
