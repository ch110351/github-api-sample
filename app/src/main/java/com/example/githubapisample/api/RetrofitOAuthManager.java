package com.example.githubapisample.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitOAuthManager {
    private static RetrofitOAuthManager mInstance = new RetrofitOAuthManager();
    private GithubService githubService;

    private RetrofitOAuthManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        githubService = retrofit.create(GithubService.class);
    }

    public static GithubService getOAuth() {
        return mInstance.githubService;
    }
}
