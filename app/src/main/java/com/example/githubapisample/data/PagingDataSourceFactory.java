package com.example.githubapisample.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class PagingDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<PagingDataSource> mutableLiveData;
    private PagingDataSource pagingDataSource;
    private String user;
    private String repo;

    public PagingDataSourceFactory(String user, String repo) {
        this.mutableLiveData = new MutableLiveData<PagingDataSource>();
        this.user = user;
        this.repo = repo;
    }

    @Override
    public DataSource create() {
        pagingDataSource = new PagingDataSource(user, repo);
        mutableLiveData.postValue(pagingDataSource);
        return pagingDataSource;
    }

    public MutableLiveData<PagingDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
