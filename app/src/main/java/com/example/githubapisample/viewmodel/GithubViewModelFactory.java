package com.example.githubapisample.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.githubapisample.data.DataModel;
import com.example.githubapisample.ui.collaborator.CollaboratorsViewModel;
import com.example.githubapisample.ui.commit.CommitViewModel;
import com.example.githubapisample.ui.MainViewModel;
import com.example.githubapisample.ui.repositories.UserViewModel;

public class GithubViewModelFactory implements ViewModelProvider.Factory {

    private DataModel dataModel;

    public GithubViewModelFactory() {
        this.dataModel = new DataModel();
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(dataModel);
        } else if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(dataModel);
        } else if (modelClass.isAssignableFrom(CollaboratorsViewModel.class)) {
            return (T) new CollaboratorsViewModel(dataModel);
        } else if (modelClass.isAssignableFrom(CommitViewModel.class)) {
            return (T) new CommitViewModel(dataModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
