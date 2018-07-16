package ru.gcsales.app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequests {
    @GET("shops")
    Call<List<Shop>> getShops();
}
