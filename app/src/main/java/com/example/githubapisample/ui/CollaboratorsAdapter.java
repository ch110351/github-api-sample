package com.example.githubapisample.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubapisample.data.model.Contributors;
import com.example.githubapisample.databinding.CollaboratorItemsBinding;

import java.util.List;
import java.util.Objects;

public class CollaboratorsAdapter extends RecyclerView.Adapter<CollaboratorsAdapter.CollaboratorViewHolder> {
    private List<Contributors> items;

    CollaboratorsAdapter(List<Contributors> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CollaboratorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CollaboratorItemsBinding collaboratorItemsBinding = CollaboratorItemsBinding.inflate(layoutInflater, parent, false);
        return new CollaboratorViewHolder(collaboratorItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CollaboratorViewHolder holder, int position) {
        Contributors contributors = items.get(position);
        holder.bind(contributors);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CollaboratorViewHolder extends RecyclerView.ViewHolder {
        private CollaboratorItemsBinding collaboratorItemsBinding;

        CollaboratorViewHolder(CollaboratorItemsBinding binding) {
            super(binding.getRoot());
            this.collaboratorItemsBinding = binding;
        }

        void bind(Contributors contributors) {
            collaboratorItemsBinding.setCollaborator(contributors);
            collaboratorItemsBinding.executePendingBindings();
        }
    }

    void swapItems(List<Contributors> newItems) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new CollaboratorsAdapter.RepoDiffCallback(this.items, newItems));
        this.items.clear();
        this.items.addAll(newItems);
        result.dispatchUpdatesTo(this);
    }

    private class RepoDiffCallback extends DiffUtil.Callback {

        private List<Contributors> mOldList;
        private List<Contributors> mNewList;

        RepoDiffCallback(List<Contributors> oldList, List<Contributors> newList) {
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
            Contributors oldRepo = mOldList.get(oldItemPosition);
            Contributors newRepo = mNewList.get(newItemPosition);
            return Objects.equals(oldRepo, newRepo);
        }
    }


}
