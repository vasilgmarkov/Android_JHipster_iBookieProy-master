package com.example.basketball.controller.activities.master_detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.controller.managers.UserLoginManager;
import com.example.basketball.controller.services.PlayerService;
import com.example.basketball.model.Apuesta;
import com.example.basketball.util.CustomProperties;

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
    private Retrofit retrofit;
    private Context context;
    private PlayerService playerService;
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

    public void setUp(List<Apuesta> bet){

          ((TextView) rootView.findViewById(R.id.Draw)).setText(bet.get(0).getaApostarName().toString());
          ((TextView) rootView.findViewById(R.id.awayTeam)).setText(bet.get(2).getaApostarName().toString());
          ((Button) rootView.findViewById(R.id.homeTeam)).setText(bet.get(1).getaApostarName().toString());


      }




}