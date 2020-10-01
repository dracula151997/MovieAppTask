package com.yelloco.movieapp

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.yelloco.movieapp.R

class Utils {
    companion object {
        fun loadImage(imageView: ImageView, path: String) {
            Glide.with(imageView.context)
                .load(path)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(imageView)
        }
    }
}