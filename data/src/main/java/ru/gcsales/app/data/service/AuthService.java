package ru.gcsales.app.data.service;


import com.google.gson.annotations.SerializedName;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit service for authenticating users.
 *
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public interface AuthService {

    /**
     * Performs login
     *
     * @param userInfo username and password body
     * @return {@link Single} of JWT token string
     */
    @POST("login")
    Single<String> login(@Body UserInfo userInfo);

    /**
     * Request body to perform login and register.
     */
    class UserInfo {
        @SerializedName("username")
        private String username;
        @SerializedName("password")
        private String password;

        public UserInfo(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
