package com.yelloco.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yelloco.movieapp.models.image.PersonImagesResponse
import com.yelloco.movieapp.network.NetworkingState
import com.yelloco.movieapp.repository.PersonImageProfileRepository
import io.reactivex.disposables.CompositeDisposable

class PersonImagesViewModel(
    private val personImageProfileRepository: PersonImageProfileRepository,
    private val compositeDisposable: CompositeDisposable
) :
    ViewModel() {

    fun fetchImages(personId: Int): LiveData<PersonImagesResponse> {
        return personImageProfileRepository.fetchImages(personId)
    }

    val networkState: LiveData<NetworkingState> by lazy {
        personImageProfileRepository.getNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}