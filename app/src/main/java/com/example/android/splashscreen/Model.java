package com.example.android.splashscreen;

public class Model {

    private String mHomeLogo, mAwayLogo;
    private String mTeamHome, mTeamAway, mMatchTime;

    public Model(String mHomeLogo, String mAwayLogo, String mTeamHome, String mTeamAway, String mMatchTime) {
        this.mHomeLogo = mHomeLogo;
        this.mAwayLogo = mAwayLogo;
        this.mTeamHome = mTeamHome;
        this.mTeamAway = mTeamAway;
        this.mMatchTime = mMatchTime;
    }

    public void setHomeLogo(String mHomeLogo) {
        this.mHomeLogo = mHomeLogo;
    }

    public void setAwayLogo(String mAwayLogo) {
        this.mAwayLogo = mAwayLogo;
    }

    public void setTeamHome(String mTeamHome) {
        this.mTeamHome = mTeamHome;
    }

    public void setTeamAway(String mTeamAway) {
        this.mTeamAway = mTeamAway;
    }

    public void setMatchTime(String mMatchTime) {
        this.mMatchTime = mMatchTime;
    }

    public String getHomeLogo() {
        return mHomeLogo;
    }

    public String getAwayLogo() {
        return mAwayLogo;
    }

    public String getTeamHome() {
        return mTeamHome;
    }

    public String getTeamAway() {
        return mTeamAway;
    }

    public String getMatchTime() {
        return mMatchTime;
    }
}
