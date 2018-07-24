package ru.gcsales.app.injection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.repository.ShopRepository;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ShopRepository provideShopRepository() {
        return new ShopRepository() {
            @Override
            public Observable<List<Shop>> getShops() {
                Shop shop = new Shop(1);
                shop.setImageUrl("http://gcsales.ru/static/dixy.png");
                shop.setName("Дикси");

                List<Shop> shops = new ArrayList<>();
                shops.add(shop);
                shops.add(shop);
                shops.add(shop);
                shops.add(shop);
                shops.add(shop);

                return Observable.just(shops);
            }
        };
    }
}
