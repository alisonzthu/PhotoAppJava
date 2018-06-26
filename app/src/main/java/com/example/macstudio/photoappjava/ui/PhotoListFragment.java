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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macstudio.photoappjava.R;
import com.example.macstudio.photoappjava.db.DataGenerator;
import com.example.macstudio.photoappjava.networking.models.PhotoData;
import com.example.macstudio.photoappjava.viewModel.SharedViewModel;
import com.example.macstudio.photoappjava.viewModel.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class PhotoListFragment extends Fragment {
    //todo: replace with databinding:
    private RecyclerView mRecyclerview;
    private static final int columnCount = 2;

    @Inject
    ViewModelFactory viewModelFactory;
    private SharedViewModel mViewModel;

    // OR, we can get the viewmodel for this fragment in its onActivityCreated() callback


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.photo_list_fragment, container, false);
        mRecyclerview = view.findViewById(R.id.photo_list);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), columnCount);
        final List<String> dummyData = DataGenerator.getDummyData();
        mRecyclerview.setAdapter(new PhotoListAdapter(dummyData));
        mRecyclerview.setLayoutManager(gridLayoutManager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(SharedViewModel.class);
        mViewModel.getPhotoDataList().observe(this, new Observer<List<PhotoData>>() {
            @Override
            public void onChanged(@Nullable List<PhotoData> photoData) {
                //update UI
                Log.d("alison", "photo data changed");
            }
        });
    }
}
