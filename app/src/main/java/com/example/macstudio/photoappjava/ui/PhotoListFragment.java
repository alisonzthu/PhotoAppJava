package com.example.macstudio.photoappjava.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macstudio.photoappjava.R;
import com.example.macstudio.photoappjava.networking.models.PhotoItem;
import com.example.macstudio.photoappjava.viewModel.SharedViewModel;
import com.example.macstudio.photoappjava.viewModel.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class PhotoListFragment extends Fragment {
    //todo: replace with databinding:
    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
    private static final int columnCount = 2;

    @Inject
    ViewModelFactory viewModelFactory;
    private SharedViewModel mViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.photo_list_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.photo_list);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), columnCount);
        mPhotoAdapter = new PhotoAdapter(mViewModel);
        mRecyclerView.setAdapter(mPhotoAdapter);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }

    // todo: what's the difference of providing viewModel in onCreateView and onActivityCreated?
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(SharedViewModel.class);
        mViewModel.getPhotoDataList().observe(this, new Observer<List<PhotoItem>>() {
            @Override
            public void onChanged(@Nullable List<PhotoItem> photoData) {
//                Log.d("alison", "photo data changed");
                // update photoData inside adapter
                mPhotoAdapter.setPhotoData(photoData);
            }
        });

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), columnCount);
        mPhotoAdapter = new PhotoAdapter(mViewModel);
        mRecyclerView.setAdapter(mPhotoAdapter);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }
}
