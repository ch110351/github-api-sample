package com.example.githubapisample.ui;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class CollaboratorsBinding {
    @BindingAdapter("imageUrl")
    public static void bindImage(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
