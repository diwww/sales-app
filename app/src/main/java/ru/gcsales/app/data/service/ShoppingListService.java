package ru.gcsales.app.data.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.gcsales.app.data.model.remote.ShoppingListResponse;
import ru.gcsales.app.domain.model.ShoppingList;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListService {

    @GET("shoplist?mode=full")
    Observable<List<ShoppingListResponse>> getShoppingLists(@Header("Authorization") String auth);

    @POST("shoplist")
    Observable<ShoppingListResponse> addShoppingList(@Header("Authorization") String auth, @Body ShoppingListBody body);

    @DELETE("shoplist/{id}")
    Observable<String> removeShoppingList(@Header("Authorization") String auth, @Path("id") long id);

    @GET("shoplist/{id}")
    Observable<ShoppingListResponse> getShoppingList(@Header("Authorization") String auth, @Path("id") long id);

    @POST("shoplist/{id}/additem")
    Observable<String> addItem(@Header("Authorization") String auth, @Path("id") long shoppingListId, @Query("id") long itemId);

    @DELETE("shoplist/{id}/deleteitem")
    Observable<String> deleteItem(@Header("Authorization") String auth, @Path("id") long shoppingListId, @Query("id") long itemId);

    class ShoppingListBody {
        @SerializedName("name")
        private String name;

        public ShoppingListBody(String name) {
            this.name = name;
        }
    }
}
