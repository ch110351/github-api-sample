package com.example.githubapisample.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.githubapisample.R;
import com.example.githubapisample.api.ApiResponse;
import com.example.githubapisample.data.model.Repository;
import com.example.githubapisample.databinding.UserRepoFragmentBinding;
import com.example.githubapisample.viewmodel.GithubViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class UserRepoFragment extends Fragment implements UserRepoAdapter.OnUserRepoListener {

    public static final String TAG = "UserRepo";
    public UserViewModel mViewModel;
    private UserRepoFragmentBinding binding;
    private GithubViewModelFactory factory = new GithubViewModelFactory();
    private UserRepoAdapter userRepoAdapter = new UserRepoAdapter(new ArrayList<Repository>(), this);

    public static UserRepoFragment newInstance() {
        return new UserRepoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UserRepoFragmentBinding.inflate(inflater, container, false);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(userRepoAdapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
        binding.setViewModel(mViewModel);
        mViewModel.getUserRepos().observe(getViewLifecycleOwner(), new Observer<ApiResponse<List<Repository>>>() {
            @Override
            public void onChanged(ApiResponse<List<Repository>> listApiResponse) {
                if (listApiResponse.isSuccessful()) {
                    Log.d("Wesley", "UserRepos onChanged  ");
                    userRepoAdapter.swapItems(listApiResponse.body);
                } else {
                    String msg = listApiResponse.errorMessage;
                    Log.d("Wesley", "erro rMessage : " + msg);
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getUserRepos();
    }

    private void getUserRepos() {
        SharedPreferences sharedPreferences = getActivity().getApplication().getSharedPreferences("data", MODE_PRIVATE);
        String userLogin = sharedPreferences.getString("login", "");
        mViewModel.searchUserRepos(userLogin);
    }

    @Override
    public void onRepoClick(String repoId) {
        Log.d("Wesley", "repoId " + repoId);
        SharedPreferences sharedPreferences = getActivity().getApplication().getSharedPreferences("data", MODE_PRIVATE);
        sharedPreferences.edit().putString("repoId", repoId).apply();//repo ID
        String tag = DetailFragment.TAG;
        DetailFragment fragment = DetailFragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack(DetailFragment.class.getSimpleName())
                .commit();
    }
}
