package com.example.android.splashscreen;

import android.content.Intent;

public class MatchData {
    int id;
    Integer home_score, away_score;
    String team_home_id, team_away_id, event_id, date;
    Boolean past;
    Model model;

    public int getId() {
        return id;
    }

    public Model getModel() {
        return model;
    }

    public MatchData(int id, Integer home_score, Integer away_score, String team_home_id, String team_away_id, String event_id, String date, Boolean past, Model model) {
        this.id = id;
        this.home_score = home_score;
        this.away_score = away_score;
        this.team_home_id = team_home_id;
        this.team_away_id = team_away_id;
        this.event_id = event_id;
        this.date = date;
        this.past = past;
        this.model = model;
    }

    public int getHome_score() {
        return home_score;
    }

    public void setHome_score(int home_score) {
        this.home_score = home_score;
    }

    public int getAway_score() {
        return away_score;
    }

    public void setAway_score(int away_score) {
        this.away_score = away_score;
    }

    public String getTeam_home_id() {
        return team_home_id;
    }

    public void setTeam_home_id(String team_home_id) {
        this.team_home_id = team_home_id;
    }

    public String getTeam_away_id() {
        return team_away_id;
    }

    public void setTeam_away_id(String team_away_id) {
        this.team_away_id = team_away_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPast() {
        return past;
    }

    public void setPast(boolean past) {
        this.past = past;
    }
}
