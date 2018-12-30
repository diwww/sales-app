package ru.gcsales.app.data.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.gcsales.app.data.service.AuthService;
import ru.gcsales.app.data.service.CategoryService;
import ru.gcsales.app.data.service.ItemService;
import ru.gcsales.app.data.service.ShopService;
import ru.gcsales.app.data.service.ShoppingListService;

/**
 * Dagger module which provides Retrofit services.
 *
 * @author Maxim Surovtsev
 * Created on 8/17/18
 */
@Module
public class NetworkModule {

    private static final String API_BASE_URL = "http://gcsales.ru/api/";
    private static final String AUTH_BASE_URL = "http://gcsales.ru/auth/";
    private final Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private final Retrofit.Builder builder = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson));

    @Provides
    @Singleton
    public ShopService provideShopService() {
        return builder.baseUrl(API_BASE_URL).build().create(ShopService.class);
    }

    @Provides
    @Singleton
    public ItemService provideItemService() {
        return builder.baseUrl(API_BASE_URL).build().create(ItemService.class);
    }

    @Provides
    @Singleton
    public AuthService provideAuthService() {
        return builder.baseUrl(AUTH_BASE_URL).build().create(AuthService.class);
    }

    @Provides
    @Singleton
    public ShoppingListService provideShoppingListService() {
        return builder.baseUrl(API_BASE_URL).build().create(ShoppingListService.class);
    }

    @Provides
    @Singleton
    public CategoryService provideCategoryService() {
        return builder.baseUrl(API_BASE_URL).build().create(CategoryService.class);
    }
}
