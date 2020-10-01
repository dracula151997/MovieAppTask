package com.yelloco.movieapp.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yelloco.movieapp.Utils
import com.yelloco.movieapp.adapters.listeners.OnImageClickListener
import com.yelloco.movieapp.models.image.ProfileImage
import com.yelloco.movieapp.network.PROFILE_IMAGE_URL_500
import kotlinx.android.synthetic.main.fragment_person_details.view.*

class ProfileImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(imagesResponse: ProfileImage, imageClickListener: OnImageClickListener) {
        with(imagesResponse)
        {
            Utils.loadImage(itemView.person_image_view, PROFILE_IMAGE_URL_500 + filePath)
            itemView.setOnClickListener { imageClickListener(itemView, filePath) }
        }
    }
}