package com.yelloco.movieapp.repository

import androidx.lifecycle.LiveData
import com.yelloco.movieapp.datasource.PersonImageProfilesDataSource
import com.yelloco.movieapp.models.image.PersonImagesResponse
import com.yelloco.movieapp.network.NetworkingState

class PersonImageProfileRepository(private val personImageProfileRepository: PersonImageProfilesDataSource) {

    fun fetchImages(personId: Int): LiveData<PersonImagesResponse> {
        personImageProfileRepository.fetchProfileImages(personId)

        return personImageProfileRepository.profileImagesLiveData
    }

    fun getNetworkState(): LiveData<NetworkingState> {
        return personImageProfileRepository.networkStateLiveData
    }
}