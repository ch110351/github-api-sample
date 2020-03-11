package com.example.githubapisample.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubapisample.data.model.Repository;
import com.example.githubapisample.databinding.UserRepoItemsBinding;

import java.util.List;
import java.util.Objects;

public class UserRepoAdapter extends RecyclerView.Adapter<UserRepoAdapter.UserRepoViewHolder> {

    private List<Repository> items;
    private OnUserRepoListener mOnUserRepoListener;

    UserRepoAdapter(List<Repository> items, OnUserRepoListener onUserRepoListener) {
        this.items = items;
        this.mOnUserRepoListener = onUserRepoListener;
    }

    @NonNull
    @Override
    public UserRepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        UserRepoItemsBinding binding = UserRepoItemsBinding.inflate(layoutInflater, parent, false);
        return new UserRepoViewHolder(binding, mOnUserRepoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRepoViewHolder holder, int position) {
        Repository repository = items.get(position);
        holder.bind(repository);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void clearItems() {
        int size = this.items.size();
        this.items.clear();
        notifyItemRangeRemoved(0, size);
    }

    void swapItems(List<Repository> newItems) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new UserRepoAdapter.RepoDiffCallback(this.items, newItems));
        this.items.clear();
        this.items.addAll(newItems);
        result.dispatchUpdatesTo(this);
    }

    private class RepoDiffCallback extends DiffUtil.Callback {

        private List<Repository> mOldList;
        private List<Repository> mNewList;

        RepoDiffCallback(List<Repository> oldList, List<Repository> newList) {
            this.mOldList = oldList;
            this.mNewList = newList;
        }

        @Override
        public int getOldListSize() {
            return mOldList != null ? mOldList.size() : 0;
        }

        @Override
        public int getNewListSize() {
            return mNewList != null ? mNewList.size() : 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            int oldId = mOldList.get(oldItemPosition).getId();
            int newId = mNewList.get(newItemPosition).getId();
            return Objects.equals(oldId, newId);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Repository oldRepo = mOldList.get(oldItemPosition);
            Repository newRepo = mNewList.get(newItemPosition);
            return Objects.equals(oldRepo, newRepo);
        }
    }

    public class UserRepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private UserRepoItemsBinding binding;
        private OnUserRepoListener onUserRepoListener;
        private String repoName;

        UserRepoViewHolder(UserRepoItemsBinding binding, OnUserRepoListener onUserRepoListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onUserRepoListener = onUserRepoListener;
            binding.getRoot().setOnClickListener(this);
        }

        void bind(Repository repository) {
            binding.setUserRepo(repository);
            binding.executePendingBindings();
            this.repoName  = repository.getName();
        }

        @Override
        public void onClick(View v) {
            onUserRepoListener.onRepoClick(repoName);
        }
    }

    public interface OnUserRepoListener {
        void onRepoClick(String reposId);
    }

}
