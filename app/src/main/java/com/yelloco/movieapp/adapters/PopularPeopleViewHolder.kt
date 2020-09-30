package com.yelloco.movieapp.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yelloco.movieapp.models.popular.Person
import com.yelloco.movieapp.network.PROFILE_IMAGE_URL_500
import kotlinx.android.synthetic.main.list_item_popular_people.view.*

class PopularPeopleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(person: Person, context: Context) {
        itemView.list_item_popular_people_text.text = person.name
        Glide.with(itemView.context)
            .load(PROFILE_IMAGE_URL_500 + person?.profilePath)
            .into(itemView.list_item_popular_people_image)

    }
}