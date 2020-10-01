package com.yelloco.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.yelloco.movieapp.R
import com.yelloco.movieapp.adapters.PopularPeopleAdapter
import com.yelloco.movieapp.adapters.listeners.OnItemClickListener
import com.yelloco.movieapp.network.NetworkingState
import com.yelloco.movieapp.viewmodel.PopularPeopleViewModel
import kotlinx.android.synthetic.main.fragment_popular_poeple.*
import org.koin.android.viewmodel.ext.android.viewModel

class PopularPeopleFragment : Fragment() {
    private val popularPeopleViewModel: PopularPeopleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_poeple, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populatePeopleRecyclerView()
    }

    private fun populatePeopleRecyclerView() {
        val popularPeopleAdapter =
            PopularPeopleAdapter(requireContext(), object : OnItemClickListener {
                override fun invoke(view: View, personId: Int) {
                    val bundle = Bundle()
                    bundle.putInt("PERSON_ID", personId)
                    Navigation.findNavController(view)
                        .navigate(
                            R.id.action_popularPeopleFragment_to_personDetailsFragment,
                            bundle
                        )
                }
            })
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = popularPeopleAdapter.getItemViewType(position)
                return if (viewType == popularPeopleAdapter.PEOPLE_VIEW_TYPE) 1
                else 3
            }
        }

        popular_people_recycler.layoutManager = gridLayoutManager
        popular_people_recycler.setHasFixedSize(true)
        popular_people_recycler.adapter = popularPeopleAdapter

        popularPeopleViewModel.popularPeople.observe(viewLifecycleOwner, {
            popularPeopleAdapter.submitList(it)
        })

        popularPeopleViewModel.networkingState.observe(viewLifecycleOwner, {

            if (popularPeopleViewModel.listIsEmpty() && it == NetworkingState.LOADING) {
                loading_data_progress_bar.visibility = View.VISIBLE
                progress_msg.visibility = View.VISIBLE
                progress_msg.text = it.message

            } else {
                loading_data_progress_bar.visibility = View.GONE
                progress_msg.visibility = View.GONE
            }
            error_msg_text_view.visibility =
                if (popularPeopleViewModel.listIsEmpty() && it == NetworkingState.ERROR) View.VISIBLE else View.GONE

            if (!popularPeopleViewModel.listIsEmpty())
                popularPeopleAdapter.setNetworkState(it)
        })
    }
}