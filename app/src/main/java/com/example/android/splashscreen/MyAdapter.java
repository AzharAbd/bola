package com.example.android.splashscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Model> mModels;

    public MyAdapter(Context context, ArrayList<Model> models) {
        mContext = context;
        mModels = models;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mHomeLogo, mAwayLogo;
        public TextView mTeamHome, mTeamAway, mMatchTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mHomeLogo = itemView.findViewById(R.id.home_logo);
            mAwayLogo = itemView.findViewById(R.id.away_logo);
            mTeamHome = itemView.findViewById(R.id.team_home);
            mTeamAway = itemView.findViewById(R.id.team_away);
            mMatchTime = itemView.findViewById(R.id.match_time);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myHolder, int position) {
        Model model = mModels.get(position);

        String home_logo = model.getHomeLogo();
        String team_home = model.getTeamHome();
        String match_time = model.getMatchTime();
        String team_away = model.getTeamAway();
        String away_logo = model.getAwayLogo();

        Picasso.get().load(home_logo).fit().centerInside().into(myHolder.mHomeLogo);
        myHolder.mTeamHome.setText(team_home);
        myHolder.mMatchTime.setText(match_time);
        myHolder.mTeamAway.setText(team_away);
        Picasso.get().load(away_logo).fit().centerInside().into(myHolder.mAwayLogo);
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }
}
