package com.yelloco.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yelloco.movieapp.models.details.PersonDetailsResponse
import com.yelloco.movieapp.network.NetworkingState
import com.yelloco.movieapp.repository.PersonDetailsRepository
import io.reactivex.disposables.CompositeDisposable

class PersonDetailsViewModel(
    private val personDetailsRepository: PersonDetailsRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    fun fetchPersonDetails(personId: Int) : LiveData<PersonDetailsResponse>
    {
        return personDetailsRepository.fetchPersonDetails(personId)
    }

    val networkState : LiveData<NetworkingState> by lazy {
        personDetailsRepository.getNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}