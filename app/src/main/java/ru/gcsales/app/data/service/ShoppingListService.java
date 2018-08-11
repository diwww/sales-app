package ru.gcsales.app.data.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.gcsales.app.domain.model.ShoppingListPreview;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListService {

    @GET("shoplist?mode=preview")
    Observable<List<ShoppingListPreview>> getPreviews(@Header("Authorization") String auth);

    @POST("shoplist")
    Observable<ShoppingListPreview> addShoppingList(@Header("Authorization") String auth, @Body ShoppingListBody body);

    @DELETE("shoplist/{id}")
    Observable<String> removeShoppingList(@Header("Authorization") String auth, @Path("id") long id);

    class ShoppingListBody {
        @SerializedName("name")
        private String name;

        public ShoppingListBody(String name) {
            this.name = name;
        }
    }
}
