package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.ProductItem;

/**
 * @author Maxim Surovtsev
 * Created on 7/29/18
 */
public interface ProductRepository {

    Observable<List<ProductItem>> getProducts(long shopId, String category, int page);
}
