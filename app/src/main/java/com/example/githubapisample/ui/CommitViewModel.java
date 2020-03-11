package com.example.githubapisample.ui;

import android.text.TextUtils;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.githubapisample.api.ApiResponse;
import com.example.githubapisample.data.DataModel;
import com.example.githubapisample.data.model.Commit;
import com.example.githubapisample.util.AbsentLiveData;

import java.util.List;

public class CommitViewModel extends ViewModel {
    private DataModel dataModel;
    private final LiveData<ApiResponse<List<Commit>>> commit;
    private final MutableLiveData<String> query = new MutableLiveData<>();
    private String userName;

    public CommitViewModel(final DataModel dataModel) {
        super();
        this.dataModel = dataModel;
        commit = Transformations.switchMap(query, new Function<String, LiveData<ApiResponse<List<Commit>>>>() {
            @Override
            public LiveData<ApiResponse<List<Commit>>> apply(String repo) {
                if (TextUtils.isEmpty(repo)) {
                    return AbsentLiveData.create();
                } else {
                    return dataModel.getCommitList(userName, repo);
                }
            }
        });
    }

    public LiveData<ApiResponse<List<Commit>>> getReposCommit() {
        return commit;
    }

    public void searchReposCommit(String userName, String repo) {
        this.userName = userName;
        query.setValue(repo);
    }
}
