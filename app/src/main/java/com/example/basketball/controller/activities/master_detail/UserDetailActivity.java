package com.example.basketball.controller.activities.master_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.activities.main.MainActivity;

import java.util.ArrayList;

/**
 * Created by usu27 on 11/4/16.
 */
public class UserDetailActivity extends AppCompatActivity{
    TextView userName,userSaldo;
    Button deportes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlayout);

        userName = (TextView) findViewById(R.id.userName);
        userSaldo = (TextView) findViewById(R.id.userSaldo);
        deportes = (Button) findViewById(R.id.deportes);
        userName.setText("Name : "+MainActivity.userInfos.getLogin());
        userSaldo.setText("Balance : "+MainActivity.userInfos.getSaldo().toString());



    }


}
