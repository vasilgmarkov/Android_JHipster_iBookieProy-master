package com.example.basketball.controller.activities.master_detail;

import com.example.basketball.model.Apuesta;
import com.example.basketball.model.User;

import java.util.List;

/**
 * Created by usu27 on 11/4/16.
 */
public interface UserCallBack {


    void onSuccess(User userInfo);

    void onFailure(Throwable t);
}
