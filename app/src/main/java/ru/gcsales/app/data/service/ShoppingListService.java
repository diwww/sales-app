package ru.gcsales.app.data.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import ru.gcsales.app.domain.model.ShoppingListPreview;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListService {

    @GET("shoplist?mode=preview")
    Observable<List<ShoppingListPreview>> getPreviews(@Header("Authorization") String auth);
}
