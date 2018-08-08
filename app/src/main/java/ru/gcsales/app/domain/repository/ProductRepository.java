package ru.gcsales.app.domain.repository;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.ProductsInfo;

/**
 * @author Maxim Surovtsev
 * Created on 7/29/18
 */
public interface ProductRepository {

    Observable<ProductsInfo> getProducts(long shopId, String category, int page);
}
