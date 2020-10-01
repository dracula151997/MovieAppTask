package com.yelloco.movieapp.repository

import androidx.lifecycle.LiveData
import com.yelloco.movieapp.datasource.PersonDetailsDataSource
import com.yelloco.movieapp.models.details.PersonDetailsResponse
import com.yelloco.movieapp.network.NetworkingState

class PersonDetailsRepository(private val personDetailsDataSource: PersonDetailsDataSource) {

    fun fetchPersonDetails(personId: Int): LiveData<PersonDetailsResponse> {
        personDetailsDataSource.fetchPersonDetails(personId)

        return personDetailsDataSource.personDetailsLiveData
    }

    fun getNetworkState(): LiveData<NetworkingState> {
        return personDetailsDataSource.networkStateLiveData
    }
}