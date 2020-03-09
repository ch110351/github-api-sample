package com.example.githubapisample.ui;

import android.text.TextUtils;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.githubapisample.api.ApiResponse;
import com.example.githubapisample.data.DataModel;
import com.example.githubapisample.data.model.Repository;
import com.example.githubapisample.util.AbsentLiveData;

import java.util.List;

public class UserViewModel extends ViewModel {
    private DataModel dataModel;

    private final LiveData<ApiResponse<List<Repository>>> repos;
    private final MutableLiveData<String> query = new MutableLiveData<>();

    public UserViewModel(final DataModel dataModel) {
        super();
        this.dataModel = dataModel;
        repos = Transformations.switchMap(query, new Function<String, LiveData<ApiResponse<List<Repository>>>>() {
            @Override
            public LiveData<ApiResponse<List<Repository>>> apply(String input) {
                if (TextUtils.isEmpty(input)) {
                    return AbsentLiveData.create();
                } else {
                    return dataModel.getUserRepo(input);
                }
            }
        });
    }

    public LiveData<ApiResponse<List<Repository>>> getUserRepos() {
        return repos;
    }

    public void searchUserRepos(String userName) {
        //isLoading.set(true);
        query.setValue(userName);
    }
}
