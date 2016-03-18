package com.example.basketball.controller.services;

import com.example.basketball.model.Apuesta;
import com.example.basketball.model.ApuestaRealizada;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Alfredo on 28/02/2016.
 */
public interface PlayerService {
    @GET("/api/apuestass")
    Call<List<Apuesta>> getAllPlayer(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization
    );
    @GET("api/apuestass/byName/{apuestaName}")
    Call<List<Apuesta>> getApuesta1x2(


            @Header("Authorization") String Authorization,
            @Path("apuestaName") String apuestaName);

    @GET("api/apuestass/byleagueName/{leagueName}")
    Call<List<Apuesta>> getApuestasByLeagueName(


            @Header("Authorization") String Authorization,
            @Path("leagueName") String leagueName);

   @POST("api/apuestaRealizadass") // Se tiene que cambiar en un interfaz propia
    Call<ApuestaRealizada> createApuesta(


    @Header("Authorization") String Authorization,
    @Body ApuestaRealizada apuesta);

}
