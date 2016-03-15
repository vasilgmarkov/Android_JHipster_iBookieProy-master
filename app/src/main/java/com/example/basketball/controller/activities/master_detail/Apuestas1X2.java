package com.example.basketball.controller.activities.master_detail;

import android.os.Bundle;
import android.widget.Button;

import com.example.basketball.R;

/**
 * Created by usu27 on 14/3/16.
 */
public class Apuestas1X2 extends PlayerListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apuestas);
       Button uno = (Button) findViewById(R.id.homeTeam);
      //  uno.setText(ap1x2.get(0).getaApostarName().toString());

    }
}
