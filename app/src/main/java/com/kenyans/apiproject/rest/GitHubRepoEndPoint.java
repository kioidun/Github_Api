package com.kenyans.apiproject.rest;

import com.kenyans.apiproject.model.GitHubRepo;
import com.kenyans.apiproject.model.GitHubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubRepoEndPoint {
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user")String name);
}
