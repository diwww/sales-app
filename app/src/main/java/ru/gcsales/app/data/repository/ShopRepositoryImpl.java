package ru.gcsales.app.data.repository;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Maybe;
import ru.gcsales.app.domain.model.Category;
import ru.gcsales.app.domain.model.Item;
import ru.gcsales.app.domain.model.Shop;
import ru.gcsales.app.domain.repository.ShopRepository;

/**
 * Implementation of {@link ShopRepository}
 *
 * @author Maxim Surovtsev
 * Created on 7/24/18
 */
public class ShopRepositoryImpl implements ShopRepository {


    /**
     * {@inheritDoc}
     */
    @Override
    public Maybe<List<Shop>> getShops() {
        Shop shop1 = new Shop();
        shop1.setName("Дикси");
        shop1.setImageUrl("https://rosaski.com/upload/iblock/8d9/8d9d48d1b7314c34b65969c61a334169.jpg");
        Shop shop2 = new Shop();
        shop2.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Dixi.gif/151px-Dixi.gif");
        List<Shop> shops = Arrays.asList(shop1, shop2, shop1, shop2);
        return Maybe.just(shops);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Maybe<List<Category>> getCategories(Shop shop) {
        List<Category> categories = Arrays.asList(new Category("Мясо"), new Category("Овощи и фрукты"), new Category("Молоко"));
        return Maybe.just(categories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Maybe<List<Item>> getItems(Shop shop, Category category, int page) {
        Item item = new Item();
        item.setCategory(new Category("Мясо"));
        item.setName("Молоко");
        item.setOldPrice(22);
        item.setNewPrice(11);
        item.setImageUrl("http://pngimg.com/uploads/sausage/sausage_PNG5176.png");
        List<Item> items = Arrays.asList(item, item, item, item, item, item, item);
        return Maybe.just(items);
    }
}
