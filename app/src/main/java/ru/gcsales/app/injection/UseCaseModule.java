package ru.gcsales.app.injection;


import dagger.Module;
import dagger.Provides;
import ru.gcsales.app.domain.interactor.AddItem;
import ru.gcsales.app.domain.interactor.AddShoppingList;
import ru.gcsales.app.domain.interactor.DeleteItem;
import ru.gcsales.app.domain.interactor.GetProducts;
import ru.gcsales.app.domain.interactor.GetShopInfo;
import ru.gcsales.app.domain.interactor.GetShoppingList;
import ru.gcsales.app.domain.interactor.GetShoppingLists;
import ru.gcsales.app.domain.interactor.GetShops;
import ru.gcsales.app.domain.interactor.Login;
import ru.gcsales.app.domain.interactor.DeleteShoppingList;
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
    public DeleteShoppingList provideRemoveShoppingList(ShoppingListRepository shoppingListRepository) {
        return new DeleteShoppingList(shoppingListRepository);
    }

    @Provides
//    @Singleton
    public GetShoppingList provideGetShoppingList(ShoppingListRepository shoppingListRepository) {
        return new GetShoppingList(shoppingListRepository);
    }

    @Provides
//    @Singleton
    public AddItem provideAddItem(ShoppingListRepository shoppingListRepository) {
        return new AddItem(shoppingListRepository);
    }

    @Provides
//    @Singleton
    public DeleteItem provideDeleteItem(ShoppingListRepository shoppingListRepository) {
        return new DeleteItem(shoppingListRepository);
    }
}
