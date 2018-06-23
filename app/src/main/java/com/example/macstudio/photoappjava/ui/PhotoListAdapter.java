package com.example.macstudio.photoappjava.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.macstudio.photoappjava.R;

import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.MyViewHolder> {

    private List<String> mPhotoData;

    public PhotoListAdapter(List<String> photoData) {
        mPhotoData = photoData;
    }

    //todo: add a data setter to help calculate DiffUtil.DiffResult

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.likesCount.setText(mPhotoData.get(position));
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
}
