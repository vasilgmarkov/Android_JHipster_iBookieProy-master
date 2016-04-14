package com.example.basketball.controller.activities.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basketball.R;
import com.example.basketball.controller.activities.master_detail.PlayerListActivity;
import com.example.basketball.controller.activities.master_detail.UserCallBack;
import com.example.basketball.controller.managers.UserLoginManager;
import com.example.basketball.controller.managers.UserManager;
import com.example.basketball.model.User;
import com.example.basketball.model.UserToken;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements UserCallBack {
        public static User userInfos;
         private TextView accessToken;
        private TextView saldo;
        private TextView grantType;
        private TextView refreshToken;
        private TextView expiresIn;
        private TextView scope;
        private Button button;
        ArrayList<Leagues> listLeage = new ArrayList<>();
        private ListView leagues;
        private String username;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        /*accessToken = (TextView) findViewById(R.id.access_token);
        tokenType = (TextView) findViewById(R.id.token_type);
        grantType = (TextView) findViewById(R.id.grant_type);
        refreshToken = (TextView) findViewById(R.id.refresh_token);
        expiresIn = (TextView) findViewById(R.id.expires_in);
        scope = (TextView) findViewById(R.id.scope);
        button = (Button) findViewById(R.id.main_button);*/


        }

    @Override
    protected void onResume() {
        super.onResume();
        username = getIntent().getStringExtra("userName");
        UserManager.getInstance(this.getApplicationContext()).getUserInfo(MainActivity.this, username);
        UserToken userToken = UserLoginManager.getInstance(this.getApplicationContext()).getUserToken();

        if (userToken != null) {


            leagues = (ListView) findViewById(R.id.listView);
            Leagues spain = new Leagues("Spanish La Liga Primera", "espanya");
            Leagues france = new Leagues("French Ligue 1", "francia");
            listLeage.add(spain);
            listLeage.add(france);
            leagues.setAdapter(new ContactAdapter(this, listLeage));
            leagues.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String nombreLeague = listLeage.get(position).nameLeage;
                    Intent i = new Intent(view.getContext(), PlayerListActivity.class); // intent en fragments
                    i.putExtra("nombreLeague",nombreLeague);
                    startActivity(i);
                }
            });
        } else {
            Log.e("MainActivity->", "onResume ERROR: userToken is NULL");
        }

      /*  button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PlayerListActivity.class);
                startActivity(i);
            }
        });*/
    }

    @Override
    public void onSuccess(User userInfo) {
        userInfos = userInfo;
        saldo = (TextView) findViewById(R.id.user_token_label);
        saldo.setText(userInfo.getSaldo().toString());
    }

    @Override
    public void onFailure(Throwable t) {

    }

    private class Leagues {
        public String nameLeage;
        public String img;

        public Leagues(String nameLeage, String img) {
            this.nameLeage = nameLeage;
            this.img = img;
        }
    }


    private class ViewInfo {
        TextView nombreLeage;
        ImageView img;
        Leagues contact;

        public ViewInfo(View view) {
            nombreLeage = (TextView) view.findViewById(R.id.leageName);
            img = (ImageView) view.findViewById(R.id.banderaLeage);

        }

        public void setContact(Leagues contact) {
            this.contact = contact;
            nombreLeage.setText(contact.nameLeage);
            img.setImageResource(getResources().getIdentifier(contact.img, "drawable", MainActivity.this.getPackageName()));

        }
    }

    private class ContactAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Leagues> contacts;

        public ContactAdapter(Context context, ArrayList<Leagues> contacts) {
            this.context = context;
            this.contacts = contacts;
        }

        @Override
        public int getCount() {
            return contacts.size();
        }

        @Override
        public Object getItem(int position) {
            return contacts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.leagues, parent, false);
                ViewInfo viewInfo = new ViewInfo(view);
                view.setTag(viewInfo);
            }
            ViewInfo viewInfo = (ViewInfo) view.getTag();
            Leagues contact = contacts.get(position);
            viewInfo.setContact(contact);

            return view;
        }


    }


}
