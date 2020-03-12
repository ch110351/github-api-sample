package com.example.githubapisample.ui.collaborator;

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
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.githubapisample.api.ApiResponse;
import com.example.githubapisample.data.model.Contributors;
import com.example.githubapisample.databinding.UserRepoCollaboratorBinding;
import com.example.githubapisample.viewmodel.GithubViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CollaboratorsFragment extends Fragment {

    public static final String TAG = "Collaborators";
    public CollaboratorsViewModel collaboratorsViewModel;
    private UserRepoCollaboratorBinding userRepoCollaboratorBinding;
    private GithubViewModelFactory factory = new GithubViewModelFactory();
    private CollaboratorsAdapter collaboratorsAdapter = new CollaboratorsAdapter(new ArrayList<Contributors>());
    public static CollaboratorsFragment newInstance() {
        return new CollaboratorsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userRepoCollaboratorBinding = UserRepoCollaboratorBinding.inflate(inflater, container, false);
        userRepoCollaboratorBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        userRepoCollaboratorBinding.recyclerView.setAdapter(collaboratorsAdapter);
        return userRepoCollaboratorBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        collaboratorsViewModel = ViewModelProviders.of(this, factory).get(CollaboratorsViewModel.class);
        userRepoCollaboratorBinding.setViewModel(collaboratorsViewModel);
        collaboratorsViewModel.getReposCollaborator().observe(getViewLifecycleOwner(), new Observer<ApiResponse<List<Contributors>>>() {
            @Override
            public void onChanged(ApiResponse<List<Contributors>> listApiResponse) {
                if (listApiResponse.isSuccessful()) {
                    collaboratorsAdapter.swapItems(listApiResponse.body);
                } else {
                    String msg = listApiResponse.errorMessage;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getReposContributors();
    }

    /**
     * Get repos contributors list
     */
    public void getReposContributors(){
        SharedPreferences sharedPreferences = getActivity().getApplication().getSharedPreferences("data", MODE_PRIVATE);
        String userLogin = sharedPreferences.getString("login", "");
        String repoId = sharedPreferences.getString("repoId", "");
        collaboratorsViewModel.searchReposCollaborators(userLogin,repoId);
    }
}
