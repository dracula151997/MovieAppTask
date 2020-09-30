package com.yelloco.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.yelloco.movieapp.R
import com.yelloco.movieapp.network.ORIGINAL_PROFILE_BASE_URL
import com.yelloco.movieapp.network.PROFILE_IMAGE_URL_500

class BindingUtils {
    companion object {
        @BindingAdapter("loadProfileImage500")
        fun loadProfileImage500(imageView: ImageView, profilePath: String) {
            Glide.with(imageView.context)
                .load(PROFILE_IMAGE_URL_500 + profilePath)
                .placeholder(R.drawable.placeholder)
                .into(imageView)
        }

        @BindingAdapter("loadOriginalImage")
        fun loadOriginalImage(imageView: ImageView, profilePath: String) {
            Glide.with(imageView.context)
                .load(ORIGINAL_PROFILE_BASE_URL + profilePath)
                .placeholder(R.drawable.placeholder)
                .into(imageView)
        }
    }
}