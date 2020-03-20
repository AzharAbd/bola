package com.example.android.splashscreen;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import android.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class TeamDetailsActivity extends AppCompatActivity {
    private static final String TAG = "TeamDetailsActivity";
    Button button_back;
    ImageView team_logo_detail;
    TextView team_name_detail;
    String team_id_detail;
    String id;
    ArrayList<TeamData> dataTeams;
    TeamData team;
    private RequestQueue mQueue;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_detail);
        button_back = findViewById(R.id.button_back);
        team_logo_detail = findViewById(R.id.team_logo_detail);
        team_name_detail = findViewById(R.id.team_name_detail);
        dataTeams = new ArrayList<TeamData>();
        Intent recvIntent = getIntent();
//        UNCOMMENT THIS
//        team_id_detail = recvIntent.getStringExtra(TEAM_ID);
        id = "133602";
//        Bundle bundle = new Bundle();
//        bundle.putString("TEAM_ID", id);
//        Sebelum sebelum = new Sebelum();
//        sebelum.setArguments(bundle);
//        Sekarang sekarang = new Sekarang();
//        sekarang.setArguments(bundle);

        mQueue = Volley.newRequestQueue(this);
        parseJSONTeam();
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeamDetailsActivity.this, HomeActivity.class));
            }
        });
        com.example.android.splashscreen.ui.main.SectionsPagerAdapter sectionsPagerAdapter = new com.example.android.splashscreen.ui.main.SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private void parseJSONTeam() {
        String urlTeam = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=" + id; //team_id_detail;
        JsonObjectRequest requestTeam = new JsonObjectRequest(Request.Method.GET, urlTeam, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            JSONArray jsonArray = response.getJSONArray("teams");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject team = jsonArray.getJSONObject(i);
                                String team_id = team.getString("idTeam");
                                String team_name = team.getString("strTeam");
                                String team_logo = team.getString("strTeamBadge");
                                if (id.equals(team_id)) { //if (team_id_detail.equals(team_id)){
                                    team_name_detail.setText(team_name);
                                    Picasso.get().load(team_logo).into(team_logo_detail);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(requestTeam);
    }
}


