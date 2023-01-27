package com.kenyans.apiproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kenyans.apiproject.R;
import com.kenyans.apiproject.activity.Repository_Activity;
import com.kenyans.apiproject.model.GitHubRepo;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ReposViewHolder> {

    private ArrayList<GitHubRepo> repos;
    private Context context;


    public Adapter(Context context,ArrayList<GitHubRepo> repos ) {
        this.repos = repos;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ReposViewHolder holder, int position) {
        holder.repoName.setText(repos.get(position).getName());
        holder.repoDescription.setText(repos.get(position).getDescription());
        holder.repolanguage.setText(repos.get(position).getLanguage());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ReposViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout reposLayout;
        TextView repoName;
        TextView repoDescription;
        TextView repolanguage;
        public ReposViewHolder(@NonNull View itemView) {
            super(itemView);
            //reposLayout = findViewById(R.id.repo_item_layout);
            repoName =itemView.findViewById(R.id.repoName);
            repoDescription =itemView.findViewById(R.id.repoDescription);
            repolanguage = itemView.findViewById(R.id.repoLanguage);

        }
    }
}
