package com.example.basketball.controller.services;

import com.example.basketball.model.Apuesta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

}
