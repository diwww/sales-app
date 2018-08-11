package ru.gcsales.app.injection;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.domain.interactor.AddShoppingList;
import ru.gcsales.app.domain.interactor.GetProducts;
import ru.gcsales.app.domain.interactor.GetShopInfo;
import ru.gcsales.app.domain.interactor.GetShoppingListPreviews;
import ru.gcsales.app.domain.interactor.GetShops;
import ru.gcsales.app.domain.interactor.Login;
import ru.gcsales.app.domain.interactor.RemoveShoppingList;
import ru.gcsales.app.domain.repository.AuthRepository;
import ru.gcsales.app.domain.repository.ProductRepository;
import ru.gcsales.app.domain.repository.ShopRepository;
import ru.gcsales.app.domain.repository.ShoppingListRepository;
import ru.gcsales.app.domain.repository.TokenRepository;

// TODO: make Activity scope
// (баг с переключением магазинов)
@Module
public class UseCaseModule {
    @Provides
//    @Singleton
    public GetShops provideGetShops(ShopRepository shopRepository) {
        return new GetShops(shopRepository);
    }

    @Provides
//    @Singleton
    public GetProducts provideGetProducts(ProductRepository productRepository) {
        return new GetProducts(productRepository);
    }

    @Provides
//    @Singleton
    public GetShopInfo provideGetShopInfo(ShopRepository shopRepository) {
        return new GetShopInfo(shopRepository);
    }

    @Provides
//    @Singleton
    public Login provideLogin(AuthRepository authRepository) {
        return new Login(authRepository);
    }

    @Provides
//    @Singleton
    public GetShoppingListPreviews provideGetShoppingListPreviews(ShoppingListRepository shoppingListRepository) {
        return new GetShoppingListPreviews(shoppingListRepository);
    }

    @Provides
//    @Singleton
    public AddShoppingList provideAddShoppingList(ShoppingListRepository shoppingListRepository) {
        return new AddShoppingList(shoppingListRepository);
    }

    @Provides
//    @Singleton
    public RemoveShoppingList provideRemoveShoppingList(ShoppingListRepository shoppingListRepository) {
        return new RemoveShoppingList(shoppingListRepository);
    }
}
