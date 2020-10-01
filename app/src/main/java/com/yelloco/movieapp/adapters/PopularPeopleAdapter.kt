package com.yelloco.movieapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yelloco.movieapp.R
import com.yelloco.movieapp.models.popular.Person
import com.yelloco.movieapp.network.NetworkingState

class PopularPeopleAdapter(val context: Context, val itemClickListener: OnItemClickListener) :
    PagedListAdapter<Person, RecyclerView.ViewHolder>(diffCallback()) {

    val PEOPLE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2
    private var networkState: NetworkingState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        if (viewType == PEOPLE_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.list_item_popular_people, parent, false)
            return PopularPeopleViewHolder(view, itemClickListener)
        } else {
            view = layoutInflater.inflate(R.layout.layout_network_state, parent, false)
            return NetworkStateViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == PEOPLE_VIEW_TYPE) {
            (holder as PopularPeopleViewHolder).bind(getItem(position)!!, context)
        } else {
            (holder as NetworkStateViewHolder).bind(networkState!!)
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkingState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            PEOPLE_VIEW_TYPE
        }
    }

    fun setNetworkState(newNetworkState: NetworkingState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()

        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }

    }

    class diffCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }


    }


}