package com.example.githubapisample.data;


import androidx.lifecycle.MutableLiveData;

import com.example.githubapisample.api.ApiResponse;
import com.example.githubapisample.api.GithubService;
import com.example.githubapisample.api.RetrofitManager;
import com.example.githubapisample.api.RetrofitOAuthManager;
import com.example.githubapisample.data.model.Contributors;
import com.example.githubapisample.data.model.LoginUser;
import com.example.githubapisample.data.model.RepoSearchResponse;
import com.example.githubapisample.data.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataModel {
    private GithubService githubService = RetrofitManager.getAPI();
    private GithubService githubOAuth = RetrofitOAuthManager.getOAuth();

    public MutableLiveData<ApiResponse<RepoSearchResponse>> searchRepo(String query) {
        final MutableLiveData<ApiResponse<RepoSearchResponse>> repos = new MutableLiveData<>();

        githubService.searchRepos(query)
                .enqueue(new Callback<RepoSearchResponse>() {
                    @Override
                    public void onResponse(Call<RepoSearchResponse> call, Response<RepoSearchResponse> response) {
                        repos.setValue(new ApiResponse<>(response));
                    }

                    @Override
                    public void onFailure(Call<RepoSearchResponse> call, Throwable throwable) {
                        repos.setValue(new ApiResponse<RepoSearchResponse>(throwable));
                    }
                });
        return repos;
    }

    public MutableLiveData<ApiResponse<List<Repository>>> getUserRepo(String user) {
        final MutableLiveData<ApiResponse<List<Repository>>> userRepos = new MutableLiveData<>();
        githubService.listRepositories(user)
                .enqueue(new Callback<List<Repository>>() {
                    @Override
                    public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                        userRepos.setValue(new ApiResponse<>(response));
                    }

                    @Override
                    public void onFailure(Call<List<Repository>> call, Throwable throwable) {
                        userRepos.setValue(new ApiResponse<List<Repository>>(throwable));
                    }
                });

        return userRepos;
    }

    public MutableLiveData<ApiResponse<List<Contributors>>> getContributorsList(String user, String repo, int page) {
        final MutableLiveData<ApiResponse<List<Contributors>>> repoContributors = new MutableLiveData<>();
        githubService.listContributors(user, repo, page)
                .enqueue(new Callback<List<Contributors>>() {
                    @Override
                    public void onResponse(Call<List<Contributors>> call, Response<List<Contributors>> response) {
                        repoContributors.setValue(new ApiResponse<>(response));
                    }

                    @Override
                    public void onFailure(Call<List<Contributors>> call, Throwable throwable) {
                        repoContributors.setValue(new ApiResponse<List<Contributors>>(throwable));
                    }
                });
        return repoContributors;
    }

    public MutableLiveData<ApiResponse<LoginUser>> getUserData(String accessToken) {
        final MutableLiveData<ApiResponse<LoginUser>> userData = new MutableLiveData<>();
        githubService.getUserData(accessToken)
                .enqueue(new Callback<LoginUser>() {
                    @Override
                    public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                        userData.setValue(new ApiResponse<LoginUser>(response));
                    }

                    @Override
                    public void onFailure(Call<LoginUser> call, Throwable throwable) {
                        userData.setValue(new ApiResponse<LoginUser>(throwable));
                    }
                });
        return userData;
    }
}
