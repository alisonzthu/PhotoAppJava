package com.example.macstudio.photoappjava.viewModel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.example.macstudio.photoappjava.GlideApp;

public class BindingAdapters {
    @BindingAdapter("srcCompat")
    public static void loadImage(ImageView view, String url) {
        GlideApp.with(view.getContext())
                .load(url)
                .into(view);
    }
//    public static void showHide(View view, boolean show) {
//        view.setVisibility(show ? View.VISIBLE : View.GONE);
//    }
}
