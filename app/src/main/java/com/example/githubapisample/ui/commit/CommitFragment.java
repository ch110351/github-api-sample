package com.example.githubapisample.ui.commit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.githubapisample.api.ApiResponse;
import com.example.githubapisample.data.model.Commit;
import com.example.githubapisample.databinding.UserRepoCommitBinding;
import com.example.githubapisample.viewmodel.GithubViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CommitFragment extends Fragment {
    public static final String TAG = "Commit";
    private UserRepoCommitBinding userRepoCommitBinding;
    private CommitViewModel commitViewModel;
    private GithubViewModelFactory factory = new GithubViewModelFactory();

    public static CommitFragment newInstance() {
        return new CommitFragment();
    }

    private CommitAdapter commitAdapter = new CommitAdapter(new ArrayList<Commit>());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userRepoCommitBinding = UserRepoCommitBinding.inflate(inflater, container, false);
        userRepoCommitBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        userRepoCommitBinding.recyclerView.setAdapter(commitAdapter);
        return userRepoCommitBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        commitViewModel = ViewModelProviders.of(this, factory).get(CommitViewModel.class);
        userRepoCommitBinding.setViewModel(commitViewModel);
        getCommit();
        commitViewModel.getReposCommit().observe(getViewLifecycleOwner(), new Observer<PagedList<Commit>>() {
            @Override
            public void onChanged(PagedList<Commit> pagedListApiResponse) {
                commitAdapter.submitList(pagedListApiResponse);
            }

//            @Override
//            public void onChanged(ApiResponse<List<Commit>> listApiResponse) {
//                if (listApiResponse.isSuccessful()) {
//                    commitAdapter.swapItems(listApiResponse.body);
//                } else {
//                    String msg = listApiResponse.errorMessage;
//                }
//            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void getCommit() {
        SharedPreferences sharedPreferences = getActivity().getApplication().getSharedPreferences("data", MODE_PRIVATE);
        String userLogin = sharedPreferences.getString("login", "");
        String repoId = sharedPreferences.getString("repoId", "");
        commitViewModel.searchReposCommit(userLogin, repoId);
    }
}
