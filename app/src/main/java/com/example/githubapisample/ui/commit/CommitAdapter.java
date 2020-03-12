package com.example.githubapisample.ui.commit;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubapisample.data.model.Commit;
import com.example.githubapisample.databinding.CommitItemsBinding;


import java.util.List;
import java.util.Objects;

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.CommitViewHolder> {
    private List<Commit> items;

    CommitAdapter(List<Commit> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CommitAdapter.CommitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CommitItemsBinding commitItemsBinding = CommitItemsBinding.inflate(layoutInflater, parent, false);
        return new CommitAdapter.CommitViewHolder(commitItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitAdapter.CommitViewHolder holder, int position) {
        Commit commit = items.get(position);
        holder.bind(commit);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CommitViewHolder extends RecyclerView.ViewHolder {
        private CommitItemsBinding commitItemsBinding;

        CommitViewHolder(CommitItemsBinding binding) {
            super(binding.getRoot());
            this.commitItemsBinding = binding;
        }

        void bind(Commit commit) {
            commitItemsBinding.setCommit(commit.getCommit());
            commitItemsBinding.executePendingBindings();
        }
    }

    void swapItems(List<Commit> newItems) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new CommitAdapter.RepoDiffCallback(this.items, newItems));
        this.items.clear();
        this.items.addAll(newItems);
        result.dispatchUpdatesTo(this);
    }

    private class RepoDiffCallback extends DiffUtil.Callback {

        private List<Commit> mOldList;
        private List<Commit> mNewList;

        RepoDiffCallback(List<Commit> oldList, List<Commit> newList) {
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
            int oldId = mOldList.get(oldItemPosition).getAuthor().getId();
            int newId = mNewList.get(newItemPosition).getAuthor().getId();
            return Objects.equals(oldId, newId);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Commit oldRepo = mOldList.get(oldItemPosition);
            Commit newRepo = mNewList.get(newItemPosition);
            return Objects.equals(oldRepo, newRepo);
        }
    }

}
