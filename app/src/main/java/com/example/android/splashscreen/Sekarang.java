package com.example.android.splashscreen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

public class Sekarang extends Fragment {
    RecyclerView mRecyclerView;
    private ArrayList<Model> mModels;
    MyAdapter myAdapter;
    private static final String TAG = "Sekarang";
    ArrayList<MatchData> dataMatches;
    ArrayList<TeamData> dataTeams;
    private RequestQueue mQueue;
    String team_id_check;
    private int i;

//    public Sekarang() {
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        team_id_check = getArguments().getString("TEAM_ID");
//        Log.d(TAG, "bbbb");
//        Log.d(TAG, team_id_check);
        View view = inflater.inflate(R.layout.fragment_sekarang, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_sekarang);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mModels = new ArrayList<>();
        dataMatches = new ArrayList<MatchData>();
        dataTeams = new ArrayList<TeamData>();
        mQueue = Volley.newRequestQueue(getActivity());
        parseJSONMatch();
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
        return view;
    }

    private void parseJSONMatch() {
        String urlEvent = "https://www.thesportsdb.com/api/v1/json/1/eventsnext.php?id=133602";
        JsonObjectRequest requestEvent = new JsonObjectRequest(Request.Method.GET, urlEvent, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("events");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject event = jsonArray.getJSONObject(i);
                                String team_home_id = event.getString("idHomeTeam");
                                String team_away_id = event.getString("idAwayTeam");
                                String event_id = event.getString("idEvent");
                                String team_home = event.getString("strHomeTeam");
                                String match_time = event.getString("strTime");
                                String team_away = event.getString("strAwayTeam");
                                Integer home_score = (event.isNull("intHomeScore")) ? null : event.getInt("intHomeScore");
                                Integer away_score = (event.isNull("intHomeScore")) ? null : event.getInt("intAwayScore");
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
//                                helper.insertMatchData(event_id, team_home_id, team_home, match_time, team_away_id, team_away,
//                                        home_score, away_score, past, date);
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
                            myAdapter = new MyAdapter(getActivity(), mModels);
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
}
