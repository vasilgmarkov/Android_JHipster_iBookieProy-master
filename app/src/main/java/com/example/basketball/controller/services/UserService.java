package com.example.basketball.controller.services;

import com.example.basketball.model.Apuesta;
import com.example.basketball.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by usu27 on 11/4/16.
 */
public interface UserService {

    @GET("api/users/{login}")
    Call<User> getLoginUser(


            @Header("Authorization") String Authorization,
            @Path("login") String login);



    @PUT("api/user/modificarSaldo/{saldo}")
    Call<User> modificarSaldoUser(


            @Header("Authorization") String Authorization,
            @Path("saldo") Double saldo);

}


