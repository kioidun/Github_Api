package com.kenyans.apiproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kenyans.apiproject.R;
import com.kenyans.apiproject.adapter.Adapter;
import com.kenyans.apiproject.model.GitHubRepo;
import com.kenyans.apiproject.rest.ApiClient;
import com.kenyans.apiproject.rest.GitHubRepoEndPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository_Activity extends AppCompatActivity {

    String receivedUserName;
    TextView userNameTV;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<GitHubRepo> repos = new ArrayList<>();
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        Bundle extras = getIntent().getExtras();
        receivedUserName = extras.getString("username");

        userNameTV = (TextView) findViewById(R.id.userNameTv);

        userNameTV.setText("User: " + receivedUserName);

        initRecyclerView();

        loadRepositories();



    }
    public void loadRepositories() {
        GitHubRepoEndPoint apiService =
                ApiClient.getClient().create(GitHubRepoEndPoint.class);

        Call<List<GitHubRepo>> call = apiService.getRepo(receivedUserName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {

                repos.clear();
                repos.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Repos", t.toString());
            }

        });
    }
    private void initRecyclerView() {
        recyclerView = findViewById(R.id.repos_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this,repos);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}