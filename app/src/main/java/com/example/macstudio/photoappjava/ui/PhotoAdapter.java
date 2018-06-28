package com.example.macstudio.photoappjava.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.macstudio.photoappjava.R;
import com.example.macstudio.photoappjava.databinding.PhotoListItemBinding;
import com.example.macstudio.photoappjava.networking.models.PhotoData;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    private List<PhotoData> mPhotoList = new ArrayList<>();

    public PhotoAdapter() {
        // do nothing
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final PhotoListItemBinding binding = DataBindingUtil
                                                .inflate(LayoutInflater.from(parent.getContext()),
                                                        R.layout.photo_list_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PhotoData photoItem = mPhotoList.get(position);
        // Personal note: calling setSomething, where something is the thing we bind
        // in the xml files is very important!
        holder.mBinding.setPhotoItem(photoItem);
        holder.mBinding.setOpenPhotoFragmentListener(new OpenPhotoFragmentListener());
        holder.mBinding.setLikeHandler(new LikeButtonClickHandler());
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        final PhotoListItemBinding mBinding;
        MyViewHolder(PhotoListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public void setPhotoData(@Nullable final List<PhotoData> photoList) {
        if(photoList == null) {
            return;
        }

        if(mPhotoList == null) {
            mPhotoList = photoList;
            notifyItemRangeInserted(0, photoList.size());
        } else {
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mPhotoList.size();
                }

                @Override
                public int getNewListSize() {
                    return photoList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    final String oldPhotoId = mPhotoList.get(oldItemPosition).getId();
                    final String newPhotoId = photoList.get(newItemPosition).getId();
                    //todo: use util funtion for string comparison
                    return oldPhotoId == newPhotoId;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    final PhotoData oldPhotoData = mPhotoList.get(oldItemPosition);
                    final PhotoData newPhotoData = photoList.get(newItemPosition);
                    // todo: make sure all necessary comparisons are made
                    return oldPhotoData.getLikes().equals(newPhotoData.getLikes())
                            && oldPhotoData.getImages().equals(newPhotoData.getImages())
                            && oldPhotoData.isUser_has_liked() == newPhotoData.isUser_has_liked();
                }
            });
            mPhotoList = photoList;
            diffResult.dispatchUpdatesTo(this);
        }
    }
}
