package com.example.macstudio.photoappjava.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.macstudio.photoappjava.R;
import com.example.macstudio.photoappjava.networking.models.PhotoData;

import java.util.ArrayList;
import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.MyViewHolder> {

    private List<PhotoData> mPhotoData = new ArrayList<>();

    public PhotoListAdapter() {
        // do nothing
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PhotoData data = mPhotoData.get(position);
        //todo: new binding!!!!!!
        holder.likesCount.setText(data.getId());
    }

    @Override
    public int getItemCount() {
        return mPhotoData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView likesCount;
        public MyViewHolder(View itemView) {
            super(itemView);
            likesCount = itemView.findViewById(R.id.photo_likes);
            // todo: add other view types into the view holder
        }
    }

    public void setPhotoData(@Nullable final List<PhotoData> photoData) {
        if(photoData == null) {
            return;
        }

        if(mPhotoData == null) {
            mPhotoData = photoData;
            notifyItemRangeInserted(0, photoData.size());
        } else {
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mPhotoData.size();
                }

                @Override
                public int getNewListSize() {
                    return photoData.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    final String oldPhotoId = mPhotoData.get(oldItemPosition).getId();
                    final String newPhotoId = photoData.get(newItemPosition).getId();
                    //todo: use util funtion for string comparison
                    return oldPhotoId == newPhotoId;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    final PhotoData oldPhotoData = mPhotoData.get(oldItemPosition);
                    final PhotoData newPhotoData = photoData.get(newItemPosition);
                    // todo: make sure all necessary comparisons are made
                    return oldPhotoData.getLikes().equals(newPhotoData.getLikes())
                            && oldPhotoData.getImages().equals(newPhotoData.getImages())
                            && oldPhotoData.isUser_has_liked() == newPhotoData.isUser_has_liked();
                }
            });
            mPhotoData = photoData;
            diffResult.dispatchUpdatesTo(this);
        }
    }
}
