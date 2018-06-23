package com.example.macstudio.photoappjava.ui;

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
import com.example.macstudio.photoappjava.db.DataGenerator;

import java.util.List;

public class PhotoListFragment extends Fragment {
    //todo: replace with databinding:
    private RecyclerView mRecyclerview;
    private static final int columnCount = 2;

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
}
