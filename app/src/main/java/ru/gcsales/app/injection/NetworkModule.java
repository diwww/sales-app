package ru.gcsales.app.injection;

import android.support.v4.view.ViewCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.gcsales.app.data.ProductService;
import ru.gcsales.app.data.ShopService;

@Module
public class NetworkModule {

    private static final String BASE_URL = "http://gcsales.ru/api/";
    private final Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private final Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @Provides
    @Singleton
    public ShopService provideShopService() {
        return retrofit.create(ShopService.class);
    }

    @Provides
    @Singleton
    public ProductService provideProductService() {
        return retrofit.create(ProductService.class);
    }
}
