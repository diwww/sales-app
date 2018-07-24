package ru.gcsales.app.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.gcsales.app.domain.model.Shop;

public interface ShopRepository {

    Observable<List<Shop>> getShops();
}
