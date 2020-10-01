package com.yelloco.movieapp.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yelloco.movieapp.R
import com.yelloco.movieapp.models.image.ProfileImage
import com.yelloco.movieapp.network.PROFILE_IMAGE_URL_500
import kotlinx.android.synthetic.main.fragment_person_details.view.*

class ProfileImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(imagesResponse: ProfileImage, imageClickListener: OnImageClickListener) {
        with(imagesResponse)
        {
            Glide.with(itemView.context)
                .load(PROFILE_IMAGE_URL_500 + filePath)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(itemView.person_image_view)

            itemView.setOnClickListener { imageClickListener(itemView, filePath) }
        }
    }
}