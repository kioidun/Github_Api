package com.kenyans.apiproject.model;

import com.google.gson.annotations.SerializedName;

public class GitHubUser {
    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String userName;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String following;

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("email")
    private String email;


    public GitHubUser(String userName, String login, String followers, String following, String email, String avatar) {
        this.userName = userName;
        this.login = login;
        this.followers = followers;
        this.following = following;
        this.email = email;
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public String getLogin() {
        return login;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

}
