package com.example.basketball.controller.activities.master_detail;

import com.example.basketball.model.Apuesta;

import java.util.List;

public interface PlayerCallback {
    void onSuccess(List<Apuesta> apuestaList);
    void onSuccess1(List<Apuesta> apuestaList);
    void onFailure(Throwable t);
}
