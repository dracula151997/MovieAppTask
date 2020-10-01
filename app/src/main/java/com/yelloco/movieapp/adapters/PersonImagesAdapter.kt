package com.yelloco.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yelloco.movieapp.R
import com.yelloco.movieapp.models.image.ProfileImage

class PersonImagesAdapter(val onImageClickListener: OnImageClickListener) :
    RecyclerView.Adapter<ProfileImageViewHolder>() {

    var images: List<ProfileImage> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_profile_images, parent, false)

        return ProfileImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileImageViewHolder, position: Int) {
        holder.bind(images[position], onImageClickListener)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}