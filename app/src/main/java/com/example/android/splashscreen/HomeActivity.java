package com.example.android.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    private ArrayList<Model> mModels;
    private RequestQueue mQueue;
    private int i;
//    myDbAdapter helper;
    ArrayList<MatchData> dataMatches;
    ArrayList<TeamData> dataTeams;
    Button nanti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "x ");
//        helper = new myDbAdapter(this);
        Log.d(TAG, "y ");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nanti = findViewById(R.id.button_nanti);
        nanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, TeamDetailsActivity.class));
            }
        });
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mModels = new ArrayList<>();
        dataMatches = new ArrayList<MatchData>();
        dataTeams = new ArrayList<TeamData>();
        mQueue = Volley.newRequestQueue(this);
        parseJSONMatch();
        Log.d(TAG, "z ");
//        ArrayList<MatchData> abc = helper.getMatchData();
        Log.d(TAG, "xy ");
//        Log.d(TAG, "abc " + String.valueOf(abc.size()));
        parseJSONTeam();

        for (int i = 0; i < mModels.size(); i++) {
            for (int j = 0; j < dataTeams.size(); j++) {
                if (mModels.get(i).getTeamHome().equals(dataTeams.get(j).getTeam_name())) {
                    mModels.get(i).setHomeLogo(dataTeams.get(j).getTeam_logo());
                }
                if (mModels.get(i).getTeamAway().equals(dataTeams.get(j).getTeam_name())) {
                    mModels.get(i).setAwayLogo(dataTeams.get(j).getTeam_logo());
                }
            }
        }
//        parseJSONMatchDetail();
    }

    private void parseJSONMatch() {
        Log.d(TAG, "eek ");
        String urlEvent = "https://www.thesportsdb.com/api/v1/json/1/searchfilename.php?e=English_Premier_League";
        JsonObjectRequest requestEvent = new JsonObjectRequest(Request.Method.GET, urlEvent, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("event");
                            Log.d(TAG, "uwu ");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject event = jsonArray.getJSONObject(i);

                                String team_home_id = event.getString("idHomeTeam");
                                String team_away_id = event.getString("idAwayTeam");
                                String event_id = event.getString("idEvent");
                                String team_home = event.getString("strHomeTeam");
                                String match_time = event.getString("strTime");
                                String team_away = event.getString("strAwayTeam");
                                Integer home_score = (event.isNull("intHomeScore")) ? null : event.getInt("intHomeScore");
                                Log.d(TAG, "a " + String.valueOf(home_score));
                                Integer away_score = (event.isNull("intHomeScore")) ? null : event.getInt("intAwayScore");
                                Log.d(TAG, "b " + String.valueOf(home_score));
                                String date = event.getString("strDate");
                                String urlBadge = "https://www.thesportsdb.com/images/media/team/badge/a1af2i1557005128.png";
                                Model model = new Model(urlBadge, urlBadge, team_home, team_away, match_time);
                                mModels.add(model);
                                Boolean past = true;
                                if (home_score == null) {
                                    past = false;
                                }
                                MatchData matchData = new MatchData(i, home_score, away_score, team_home_id, team_away_id,
                                        event_id, date, past, model);
                                Log.d(TAG, "akmal ");
//                                helper.insertMatchData(event_id, team_home_id, team_home, match_time, team_away_id, team_away,
//                                        home_score, away_score, past, date);
                                Log.d(TAG, "sempak ");
                                dataMatches.add(matchData);
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
        Log.d(TAG, "hahaa ");
        mQueue.add(requestEvent);
    }
    private void parseJSONTeam() {
        String urlTeam = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League";
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
                                TeamData teamData = new TeamData(i, team_id, team_name, team_logo);
                                dataTeams.add(teamData);
                                for (int j = 0; j < mModels.size(); j++) {
                                    if (mModels.get(j).getTeamHome().equals(team_name)) {
                                        mModels.get(j).setHomeLogo(team_logo);
                                    }
                                    if (mModels.get(j).getTeamAway().equals(team_name)) {
                                        mModels.get(j).setAwayLogo(team_logo);
                                    }
                                }
                            }
                        myAdapter = new MyAdapter(HomeActivity.this, mModels);
                        mRecyclerView.setAdapter(myAdapter);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
