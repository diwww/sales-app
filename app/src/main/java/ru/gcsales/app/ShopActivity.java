package ru.gcsales.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class ShopActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private ProductsAdapter mProductsAdapter;
    private LinearLayoutManager mLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        init();

    }

    private void init() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mRecyclerView = findViewById(R.id.recycler_view_products);

        mLinearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mProductsAdapter = new ProductsAdapter(this);
        mRecyclerView.setAdapter(mProductsAdapter);


        // FIXME: remove
        try {
            JSONObject object = new JSONObject("{\"id\":153462,\"name\":\"Зубная паста Рaradontax ультраочищение; без фтора, 75 мл\",\"category\":\"Непродовольственные товары\",\"oldPrice\":189.5,\"newPrice\":109.9,\"dateIn\":\"2018-06-28\",\"dateOut\":\"2018-07-11\",\"crawlDate\":\"2018-06-27\",\"condition\":\"-\",\"image\":null,\"imageUrl\":\"https://dixy.ru/upload/iblock/124/2000277369.jpg\",\"discount\":\"-42\",\"shop\":{\"id\":1,\"alias\":\"dixy\",\"name\":\"Дикси\",\"imageUrl\":\"http://gcsales.ru/static/dixy.png\"}}");
            mProductsAdapter.setData(Arrays.asList(
                    Product.fromJSON(object),
                    Product.fromJSON(object),
                    Product.fromJSON(object),
                    Product.fromJSON(object),
                    Product.fromJSON(object),
                    Product.fromJSON(object)
            ));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, ShopActivity.class);
        // TODO: put extra id and etc...
        return intent;
    }
}
