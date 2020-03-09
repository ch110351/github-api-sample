package com.example.githubapisample.ui;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.githubapisample.api.ApiResponse;
import com.example.githubapisample.data.DataModel;
import com.example.githubapisample.data.model.LoginUser;


public class MainViewModel extends ViewModel {
    private DataModel dataModel;
    private final LiveData<ApiResponse<LoginUser>> loginUserLiveData;
    private final MutableLiveData<String> token = new MutableLiveData<>();

    public MainViewModel(final DataModel dataModel) {
        super();
        this.dataModel = dataModel;
        loginUserLiveData = Transformations.switchMap(token, new Function<String, LiveData<ApiResponse<LoginUser>>>() {
            @Override
            public LiveData<ApiResponse<LoginUser>> apply(String accessToken) {
                return dataModel.getUserData(accessToken);
            }
        });
    }

    public LiveData<ApiResponse<LoginUser>> getUserData() {
        return loginUserLiveData;
    }

    public void checkToken(String accessToken) {
        token.setValue(accessToken);
    }

}
