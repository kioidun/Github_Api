package com.kenyans.apiproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kenyans.apiproject.R;
import com.kenyans.apiproject.model.GitHubUser;
import com.kenyans.apiproject.rest.ApiClient;
import com.kenyans.apiproject.rest.GitUserEndPoints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Activity extends AppCompatActivity {
    TextView userName;
    TextView login;
    TextView followers;
    TextView following;
    TextView email;
    Button repoButton;
    ImageView userPhoto;
    String newString;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);

        userName = findViewById(R.id.userName);
        login = findViewById(R.id.login);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        email = findViewById(R.id.email);
        repoButton = findViewById(R.id.repoButton);
        userPhoto = findViewById(R.id.userPhoto);

        Bundle extras = getIntent().getExtras();
        newString = extras.getString("String_that_I_NEED");

        repoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(User_Activity.this,Repository_Activity.class );
                i.putExtra("username",newString);
                startActivity(i);
            }
        });
        
        loadData();
    }

    private void loadData() {
        final GitUserEndPoints apiService = ApiClient.getClient().create(GitUserEndPoints.class);

        Call<GitHubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                userName.setText("Username: "+response.body().getUserName());
                followers.setText("Followers: "+response.body().getFollowers());
                following.setText("Following: "+response.body().getFollowing());
                login.setText("Login: "+response.body().getLogin());
                if(response.body().getEmail() == null){
                    email.setText("Email not provided");
                }else {
                    email.setText("Email: " + response.body().getEmail());
                }
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {

            }
        });
    }
}