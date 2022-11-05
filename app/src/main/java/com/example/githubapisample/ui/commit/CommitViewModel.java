package com.example.githubapisample.ui.commit;

import android.text.TextUtils;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.githubapisample.api.ApiResponse;
import com.example.githubapisample.data.DataModel;
import com.example.githubapisample.data.PagingDataSourceFactory;
import com.example.githubapisample.data.model.Commit;
import com.example.githubapisample.util.AbsentLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CommitViewModel extends ViewModel {
    private DataModel dataModel;
    private LiveData<PagedList<Commit>> commit;
    private final MutableLiveData<String> query = new MutableLiveData<>();
    private String userName;
    private String repo;
    private Executor executor;
    private PagingDataSourceFactory pagingDataSourceFactory;
    private PagedList.Config pageListConfig;

    public CommitViewModel(final DataModel dataModel) {
        super();
        executor = Executors.newFixedThreadPool(5);
        this.dataModel = dataModel;
//        commit = Transformations.switchMap(query, new Function<String, LiveData<ApiResponse<List<Commit>>>>() {
//            @Override
//            public LiveData<ApiResponse<List<Commit>>> apply(String repo) {
//                if (TextUtils.isEmpty(repo)) {
//                    return AbsentLiveData.create();
//                } else {
//                    return dataModel.getCommitList(userName, repo, 0);
//                }
//            }
//        });
//
//        commit = (new LivePagedListBuilder(pagingDataSourceFactory, pageListConfig))
//                .setFetchExecutor(executor)
//                .build();
    }

    public LiveData<PagedList<Commit>> getReposCommit() {
        return commit;
    }

    public void searchReposCommit(String userName, String repo) {
        this.userName = userName;
        this.repo = repo;
        pagingDataSourceFactory = new PagingDataSourceFactory(userName, repo);
        pageListConfig = (new PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(2).build());
        commit = (new LivePagedListBuilder(pagingDataSourceFactory, pageListConfig))
                .setFetchExecutor(executor)
                .build();
        //query.setValue(repo);
    }
}
