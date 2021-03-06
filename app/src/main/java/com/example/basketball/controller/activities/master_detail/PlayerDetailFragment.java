package com.example.basketball.controller.activities.master_detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basketball.R;
import com.example.basketball.controller.activities.main.MainActivity;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.controller.managers.UserLoginManager;
import com.example.basketball.controller.services.PlayerService;
import com.example.basketball.model.Apuesta;
import com.example.basketball.util.CustomProperties;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a single Apuesta detail screen.
 * This fragment is either contained in a {@link PlayerListActivity}
 * in two-pane mode (on tablets) or a {@link PlayerDetailActivity}
 * on handsets.
 */
public class PlayerDetailFragment extends Fragment implements PlayerCallback {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    View rootView;
    /**
     * The player content this fragment is presenting.
     */
    private Apuesta mItem;
    private List<Apuesta> ap1x2;
    private Button home, draw, away;
    private TextView homeOdd,drawOdd,awayOdd;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlayerDetailFragment() {


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            String id = getArguments().getString(ARG_ITEM_ID);
            mItem = PlayerManager.getInstance(this.getContext()).getPlayer(id);
            PlayerManager.getInstance(this.getContext()).getApuesta1x2(PlayerDetailFragment.this, mItem.getApuestaName());

            assert mItem != null;
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getLigaName());
                //llamar funcion

                //
            }
        }
    }

    @Override
    public void onSuccess(List<Apuesta> apuestaList) {
        Log.i("OnSuccers", "                     " + 2);


    }

    @Override
    public void onSuccess1(List<Apuesta> apuestaList) {
        ap1x2 = apuestaList;
        setUp(ap1x2);
    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.apuestas, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {


            ((TextView) rootView.findViewById(R.id.apuestaName)).setText("Game: " + mItem.getApuestaName().toString());
           /* if (ap1x2 == null) {

                }

                /*((TextView) rootView.findViewById(R.id.player_detail)).setText("Game: " + mItem.getApuestaName().toString());
                ((TextView) rootView.findViewById(R.id.apuestaName)).setText(ap1x2.get(1).getaApostarName().toString());
                ((Button) rootView.findViewById(R.id.homeTeam)).setText(ap1x2.get(0).getaApostarName().toString());*/

            //    Intent myIntent = new Intent(this.getContext(), Apuesta.class);
            //  startActivityForResult(myIntent, 0);

        }

        return rootView;
    }

    public void setUp(final List<Apuesta> bet) {



        int h=0,a=0,d=0;
        home = ((Button) rootView.findViewById(R.id.homeTeam));
        draw = ((Button) rootView.findViewById(R.id.Draw));
        away = ((Button) rootView.findViewById(R.id.awayTeam));
        homeOdd = (TextView) rootView.findViewById(R.id.homeId);
        drawOdd = (TextView) rootView.findViewById(R.id.drawId);
        awayOdd = (TextView) rootView.findViewById(R.id.awayId);
        String game = bet.get(0).getApuestaName();
        int hTeam = 0,aTeam = 0;
        String aTeamName="", hTeamName="";
        for (int i = 1; i < game.length(); i++) {
            if (game.charAt(i) == 'v' && game.charAt(i+1) == ' ' && game.charAt(i-1) == ' ') {
                hTeam = i;
                break;
            }
        }
        for (int i = 1; i < game.length(); i++) {
            if (game.charAt(i) == '-' && game.charAt(i+1) == ' ' && game.charAt(i-1) == ' ') {
                aTeam = i;
                break;
            }
        }



        hTeamName = game.substring(0 ,hTeam-1 );
        aTeamName = game.substring(hTeam+2, aTeam-1);


        for(int i = 0 ;i<bet.size();i++){

            if(bet.get(i).getaApostarName().toString().equals(hTeamName)){
                home.setText(bet.get(i).getaApostarName().toString());
                homeOdd.setText(bet.get(i).getaApostarOdd().toString());
                h=i;
            }
            if(bet.get(i).getaApostarName().toString().equals(aTeamName)){
                away.setText(bet.get(i).getaApostarName().toString());
                awayOdd.setText(bet.get(i).getaApostarOdd().toString());
                a=i;
            }
            if(bet.get(i).getaApostarName().toString().equals("Draw")){
                draw.setText(bet.get(i).getaApostarName().toString());
                drawOdd.setText(bet.get(i).getaApostarOdd().toString());
                d=i;
            }

        }


        final int finalH = h;
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ApuestasResumeActivity.class); // intent en fragments
                Bundle bundle = new Bundle();
                bundle.putSerializable("apuesta", bet.get(finalH));
                i.putExtras(bundle);

                startActivity(i);
            }
        });

        final int finalD = d;
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ApuestasResumeActivity.class); // intent en fragments
                Bundle bundle = new Bundle();
                bundle.putSerializable("apuesta", bet.get(finalD));
                i.putExtras(bundle);

                startActivity(i);
            }
        });

        final int finalA = a;
        away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ApuestasResumeActivity.class); // intent en fragments
                Bundle bundle = new Bundle();
                bundle.putSerializable("apuesta", bet.get(finalA));
                i.putExtras(bundle);

                startActivity(i);
            }
        });



    }


}
