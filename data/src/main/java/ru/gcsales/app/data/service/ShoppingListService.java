package ru.gcsales.app.data.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.gcsales.app.data.model.remote.ShoppingListResponse;

/**
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListService {

    @GET("shoplist?mode=full")
    Single<List<ShoppingListResponse>> getShoppingLists(@Header("Authorization") String auth);

    @POST("shoplist")
    Single<ShoppingListResponse> addShoppingList(@Header("Authorization") String auth, @Body ShoppingListBody body);

    @DELETE("shoplist/{id}")
    Single<String> removeShoppingList(@Header("Authorization") String auth, @Path("id") long id);

    @GET("shoplist/{id}")
    Single<ShoppingListResponse> getShoppingList(@Header("Authorization") String auth, @Path("id") long id);

    @POST("shoplist/{id}/additem")
    Single<String> addItem(@Header("Authorization") String auth, @Path("id") long shoppingListId, @Query("id") long itemId);

    @DELETE("shoplist/{id}/deleteitem")
    Single<String> deleteItem(@Header("Authorization") String auth, @Path("id") long shoppingListId, @Query("id") long itemId);

    class ShoppingListBody {
        @SerializedName("name")
        private String name;

        public ShoppingListBody(String name) {
            this.name = name;
        }
    }
}
