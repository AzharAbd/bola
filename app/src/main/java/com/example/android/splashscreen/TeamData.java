package com.example.android.splashscreen;

public class TeamData {
    int id;
    String team_id, team_name, team_logo;

    public TeamData(int id, String team_id, String team_name, String team_logo) {
        this.id = id;
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_logo = team_logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_logo() {
        return team_logo;
    }

    public void setTeam_logo(String team_logo) {
        this.team_logo = team_logo;
    }
}
