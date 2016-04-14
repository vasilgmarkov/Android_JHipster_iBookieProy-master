package com.example.basketball.controller.managers;

import android.content.Context;
import android.util.Log;

import com.example.basketball.controller.activities.master_detail.PlayerCallback;
import com.example.basketball.controller.activities.master_detail.UserCallBack;
import com.example.basketball.controller.services.PlayerService;
import com.example.basketball.controller.services.UserService;
import com.example.basketball.model.Apuesta;
import com.example.basketball.model.User;
import com.example.basketball.util.CustomProperties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by usu27 on 12/4/16.
 */
public class UserManager {

    private static UserManager ourInstance;
    private Retrofit retrofit;
    private Context context;
    private UserService userService;
    private User user;

    private UserManager(Context cntxt) {
        context = cntxt;
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
    }

    public static UserManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new UserManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    public synchronized void getUserInfo(final UserCallBack userCallBack,String login) {
       Call<User> call = userService.getLoginUser(UserLoginManager.getInstance(context).getBearerToken(), login);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    userCallBack.onSuccess(user);

                } else {
                    userCallBack.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                userCallBack.onFailure(t);
            }
        });
    }

    public synchronized void modificarSaldoUser(final UserCallBack userCallBack,Double saldo) {
        Call<User> call = userService.modificarSaldoUser(UserLoginManager.getInstance(context).getBearerToken(), saldo);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    userCallBack.onSuccess(user);

                } else {
                    userCallBack.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                userCallBack.onFailure(t);
            }
        });
    }

}
