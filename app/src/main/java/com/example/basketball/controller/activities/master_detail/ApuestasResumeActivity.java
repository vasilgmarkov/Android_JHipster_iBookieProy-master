package com.example.basketball.controller.activities.master_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.Apuesta;
import com.example.basketball.model.ApuestaRealizada;

import java.util.List;

/**
 * Created by usu27 on 14/3/16.
 */
public class ApuestasResumeActivity extends AppCompatActivity implements PlayerCallback {
        TextView team,cuota;
        SeekBar pasta;
        EditText pastaEdit;
        Button placeBet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apuestaresume);

    final Apuesta a = (Apuesta) getIntent().getSerializableExtra("apuesta");
        pasta = (SeekBar) findViewById(R.id.seekBar);
        pasta.setMax(100);
        pasta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                pastaEdit.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        placeBet = (Button) findViewById(R.id.apostaR);
        pastaEdit = (EditText) findViewById(R.id.pastaEdit);

        team = (TextView) findViewById(R.id.team);
        cuota = (TextView) findViewById(R.id.cuota);

        team.setText("Result "+a.getaApostarName());
        cuota.setText("Odd : "+a.getaApostarOdd().toString());
        placeBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApuestaRealizada apuestaRealizada = new ApuestaRealizada();
                apuestaRealizada.setCantidadApostada(Double.valueOf(String.valueOf(pastaEdit.getText())));
                apuestaRealizada.setCuota(a.getaApostarOdd());
                apuestaRealizada.setEventoApostado(a.getApuestaName());
                apuestaRealizada.setGanadorApuesta(a.getaApostarName());
                PlayerManager.getInstance(v.getContext()).createApuesta(ApuestasResumeActivity.this, apuestaRealizada);
                Intent i = new Intent(v.getContext(), PlayerListActivity.class); // intent en fragments

                startActivity(i);
            }
        });
    }


    @Override
    public void onSuccess(List<Apuesta> apuestaList) {

    }

    @Override
    public void onSuccess1(List<Apuesta> apuestaList) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
