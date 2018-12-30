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
 * Retrofit service for shopping lists manipulations.
 *
 * @author Maxim Surovtsev
 * Created on 8/8/18
 */
public interface ShoppingListService {

    /**
     * Gets all shopping lists.
     *
     * @param auth auth header (JWT)
     * @return {@link Single} list of shopping lists
     */
    @GET("shoplist?mode=full")
    Single<List<ShoppingListResponse>> getShoppingLists(@Header("Authorization") String auth);

    /**
     * Adds a new shopping list.
     *
     * @param auth auth header (JWT)
     * @param body request body
     * @return {@link Single} of created shopping list object
     */
    @POST("shoplist")
    Single<ShoppingListResponse> addShoppingList(@Header("Authorization") String auth, @Body ShoppingListBody body);

    /**
     * Removes a shopping list
     *
     * @param auth auth header (JWT)
     * @param id   shopping list id
     * @return {@link Single} of response string, e.g. "OK"
     */
    @DELETE("shoplist/{id}")
    Single<String> removeShoppingList(@Header("Authorization") String auth, @Path("id") long id);

    /**
     * Gets a concrete shopping list.
     *
     * @param auth auth header (JWT)
     * @param id   shopping list id
     * @return {@link Single} of shopping list object
     */
    @GET("shoplist/{id}")
    Single<ShoppingListResponse> getShoppingList(@Header("Authorization") String auth, @Path("id") long id);

    /**
     * Adds an item to the shopping list
     *
     * @param auth           auth header (JWT)
     * @param shoppingListId shopping list id
     * @param itemId         item id
     * @return {@link Single} of response string, e.g. "OK"
     */
    @POST("shoplist/{id}/additem")
    Single<String> addItem(@Header("Authorization") String auth, @Path("id") long shoppingListId, @Query("id") long itemId);

    /**
     * Removes an item from the shopping list
     *
     * @param auth           auth header (JWT)
     * @param shoppingListId shopping list id
     * @param itemId         item id
     * @return {@link Single} of response string, e.g. "OK"
     */
    @DELETE("shoplist/{id}/deleteitem")
    Single<String> deleteItem(@Header("Authorization") String auth, @Path("id") long shoppingListId, @Query("id") long itemId);

    /**
     * Request body to add a shopping list.
     */
    class ShoppingListBody {
        @SerializedName("name")
        private String name;

        public ShoppingListBody(String name) {
            this.name = name;
        }
    }
}
