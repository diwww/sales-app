package ru.gcsales.app.data.service;


import com.google.gson.annotations.SerializedName;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public interface AuthService {

    @POST("login")
    Single<String> login(@Body UserInfo userInfo);

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
