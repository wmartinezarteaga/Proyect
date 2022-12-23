package com.example.proyect.app.ui.controller;


import com.example.proyect.core.database.models.Localizacion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ModelViewServices {
    @GET("{ip}")
    Call<Localizacion> getModels(@Path("ip") String ip);

  // @FormUrlEncoded
  // @POST("upload/photo")
  // Call<SimpleResponse> postPhoto(
  //         @Field("image") String base64,
  //         @Field("extension") String extension,
  //         @Field("user_id") String user_id
  // );

  // @GET("login")
  // Call<LoginResponse> getLogin(
  //         @Query("username") String username,
  //         @Query("password") String password
  // );

  // @FormUrlEncoded
  // @POST("product")
  // Call<SimpleResponse> postNewProduct(
  //         @Field("code") String code,
  //         @Field("name") String name,
  //         @Field("description") String description
  // );

}

