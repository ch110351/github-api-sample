package com.example.githubapisample.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.githubapisample.api.ApiResponse;
import com.example.githubapisample.api.GithubService;
import com.example.githubapisample.api.RetrofitManager;
import com.example.githubapisample.data.model.Commit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagingDataSource extends PageKeyedDataSource<Integer, Commit> {
    private GithubService githubService = RetrofitManager.getAPI();
    private MutableLiveData<List<Commit>> repoCommit;
    private String user;
    private String repo;

    public PagingDataSource(String user, String repo) {
        repoCommit = new MutableLiveData<>();
        this.user = user;
        this.repo = repo;
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Commit> callback) {
        githubService.listCommitList(user, repo, 1).enqueue(new Callback<List<Commit>>() {
            @Override
            public void onResponse(Call<List<Commit>> call, Response<List<Commit>> response) {
                callback.onResult(response.body(), null, 3);
                //repoCommit.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Commit>> call, Throwable throwable) {
               // repoCommit.setValue(new ApiResponse<List<Commit>>(throwable));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Commit> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Commit> callback) {
        githubService.listCommitList(user, repo, params.key).enqueue(new Callback<List<Commit>>() {
            @Override
            public void onResponse(Call<List<Commit>> call, Response<List<Commit>> response) {
                callback.onResult(response.body(), params.key + 1);
                //repoCommit.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Commit>> call, Throwable throwable) {
                //repoCommit.setValue(new ApiResponse<List<Commit>>(throwable));
            }
        });
    }
}
