package com.yelloco.movieapp.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yelloco.movieapp.network.NetworkingState
import kotlinx.android.synthetic.main.layout_network_state.view.*

class NetworkStateViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(networkingState: NetworkingState) {
        if (networkingState == NetworkingState.LOADING) {
            itemView.loading_progress_bar.visibility = View.VISIBLE
        } else {
            itemView.loading_progress_bar.visibility = View.GONE
        }

        if (networkingState == NetworkingState.ERROR) {
            itemView.network_message.visibility = View.VISIBLE
            itemView.network_message.text = networkingState.message
        } else if (networkingState == NetworkingState.END_OF_LIST) {
            itemView.network_message.visibility = View.VISIBLE
            itemView.network_message.text = networkingState.message
        } else {
            itemView.network_message.visibility = View.GONE
        }
    }
}