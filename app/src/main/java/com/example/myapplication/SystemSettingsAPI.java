package com.example.myapplication;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SystemSettingsAPI {
    @GET("/api/v1/mb_settings.php")
    Call<SystemSettings> getSystemSettings(@Query("id") int id);
}
