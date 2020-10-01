package com.yelloco.movieapp.adapters.viewholders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yelloco.movieapp.Utils
import com.yelloco.movieapp.adapters.listeners.OnItemClickListener
import com.yelloco.movieapp.models.popular.Person
import com.yelloco.movieapp.network.PROFILE_IMAGE_URL_500
import kotlinx.android.synthetic.main.list_item_popular_people.view.*

class PopularPeopleViewHolder(view: View, val itemClick: OnItemClickListener) :
    RecyclerView.ViewHolder(view) {
    fun bind(person: Person, context: Context) {
        with(person) {
            itemView.list_item_popular_people_text.text = name
            Utils.loadImage(
                itemView.list_item_popular_people_image,
                PROFILE_IMAGE_URL_500 + profilePath
            )
            itemView.setOnClickListener { itemClick(itemView, id) }
        }


    }
}