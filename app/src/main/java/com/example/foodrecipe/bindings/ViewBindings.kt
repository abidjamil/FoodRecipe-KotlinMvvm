package com.example.foodrecipe.bindings

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("loadImage")
fun loadImageWithGlide(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerInside()
        .into(view)
}