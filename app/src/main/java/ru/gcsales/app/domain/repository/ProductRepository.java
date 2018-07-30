package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Product;

/**
 * @author Maxim Surovtsev
 * Created on 7/29/18
 */
public interface ProductRepository {

    Observable<List<Product>> getProducts(long shopId, int page);
}
