package com.kenyans.apiproject.rest;

import com.kenyans.apiproject.model.GitHubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitUserEndPoints {
    @GET("/users/{user}")
    Call<GitHubUser> getUser(@Path("user")String user);
}
