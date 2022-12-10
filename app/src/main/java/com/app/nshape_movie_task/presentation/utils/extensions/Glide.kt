package com.app.nshape_movie_task.presentation.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.app.nshape_movie_task.R

fun ImageView.load(imageUrl: String, radius: Int = 1) {
  Glide.with(this).load(imageUrl)
    .placeholder(R.drawable.ic_placeholder)
    . error(R.drawable.ic_placeholder)
    .apply(RequestOptions.bitmapTransform(RoundedCorners(radius))).into(this)
}