package com.example.githubapisample.ui;

import android.text.TextUtils;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.githubapisample.api.ApiResponse;
import com.example.githubapisample.data.DataModel;
import com.example.githubapisample.data.model.Contributors;
import com.example.githubapisample.util.AbsentLiveData;

import java.util.List;

public class CollaboratorsViewModel extends ViewModel {
    private DataModel dataModel;
    private final LiveData<ApiResponse<List<Contributors>>> collaborators;
    private final MutableLiveData<String> query = new MutableLiveData<>();
    private String userName;

    public CollaboratorsViewModel(final DataModel dataModel) {
        super();
        this.dataModel = dataModel;
        collaborators = Transformations.switchMap(query, new Function<String, LiveData<ApiResponse<List<Contributors>>>>() {
            @Override
            public LiveData<ApiResponse<List<Contributors>>> apply(String repo) {
                if (TextUtils.isEmpty(repo)) {
                    return AbsentLiveData.create();
                } else {
                    return dataModel.getContributorsList(userName, repo, 0);
                }
            }
        });
    }

    public LiveData<ApiResponse<List<Contributors>>> getReposCollaborator() {
        return collaborators;
    }

    public void searchReposCollaborators(String userName, String repo) {
        this.userName = userName;
        query.setValue(repo);
    }
}
