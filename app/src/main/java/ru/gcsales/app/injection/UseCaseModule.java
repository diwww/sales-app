package ru.gcsales.app.injection;


import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.domain.interactor.AddShoppingList;
import ru.gcsales.app.domain.interactor.GetProducts;
import ru.gcsales.app.domain.interactor.GetShopInfo;
import ru.gcsales.app.domain.interactor.GetShoppingList;
import ru.gcsales.app.domain.interactor.GetShoppingLists;
import ru.gcsales.app.domain.interactor.GetShops;
import ru.gcsales.app.domain.interactor.Login;
import ru.gcsales.app.domain.interactor.RemoveShoppingList;
import ru.gcsales.app.domain.repository.AuthRepository;
import ru.gcsales.app.domain.repository.ProductRepository;
import ru.gcsales.app.domain.repository.ShopRepository;
import ru.gcsales.app.domain.repository.ShoppingListRepository;

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
    public GetShoppingLists provideGetShoppingLists(ShoppingListRepository shoppingListRepository) {
        return new GetShoppingLists(shoppingListRepository);
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

    @Provides
//    @Singleton
    public GetShoppingList provideGetShoppingList(ShoppingListRepository shoppingListRepository) {
        return new GetShoppingList(shoppingListRepository);
    }
}
