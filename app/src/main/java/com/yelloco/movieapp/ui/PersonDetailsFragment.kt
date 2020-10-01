package com.yelloco.movieapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.yelloco.movieapp.R
import com.yelloco.movieapp.databinding.FragmentPersonDetailsBinding
import com.yelloco.movieapp.network.PROFILE_IMAGE_URL_500
import com.yelloco.movieapp.viewmodel.PersonDetailsViewModel
import com.yelloco.movieapp.viewmodel.PopularPeopleViewModel
import kotlinx.android.synthetic.main.fragment_person_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class PersonDetailsFragment : Fragment() {
    private var personID: Int = 0
    private val personDetailsViewModel: PersonDetailsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = arguments
        if (arguments != null)
        {
            personID = arguments.getInt("PERSON_ID")
            Log.d("TAG", "onViewCreated: Person Id received ${personID}")
        }

        personDetailsViewModel.fetchPersonDetails(personID).observe(viewLifecycleOwner, Observer {
            with(it){
                Glide.with(requireActivity())
                    .load(PROFILE_IMAGE_URL_500 + profilePath)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(person_image_view)
                person_name_tv.text = name
                known_for_tv.text = knownForDepartment
                birthday_tv.text = birthday
                place_of_birth_tv.text = placeOfBirth
                biography_tv.text = biography
            }
        })

        personDetailsViewModel.networkState.observe(viewLifecycleOwner, Observer {

        })


    }
}