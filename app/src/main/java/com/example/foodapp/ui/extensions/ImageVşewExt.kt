package com.example.foodapp.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

fun ImageView.loadImage(uri: String) {
    Glide.with(this)
        .load(uri)
        .centerInside()
        .transform(CircleCrop())
        .into(this)
}