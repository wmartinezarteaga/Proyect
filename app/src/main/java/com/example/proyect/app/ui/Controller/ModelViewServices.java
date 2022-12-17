package com.example.proyect.app.ui.Controller;


import com.example.proyect.core.DataBase.models.Localizacion;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ModelViewServices {
    @GET("24.48.0.1")
    Call<Localizacion> getModels();

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

