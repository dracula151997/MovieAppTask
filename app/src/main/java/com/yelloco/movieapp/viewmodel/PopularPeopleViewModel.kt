package com.yelloco.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.yelloco.movieapp.models.popular.Person
import com.yelloco.movieapp.network.NetworkingState
import com.yelloco.movieapp.repository.PopularPeopleRepository
import io.reactivex.disposables.CompositeDisposable

class PopularPeopleViewModel(
    private val popularPeopleRepository: PopularPeopleRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {
    val popularPeople: LiveData<PagedList<Person>> by lazy {
        popularPeopleRepository.fetchPeoplePagedList()
    }

    val networkingState: LiveData<NetworkingState> by lazy {
        popularPeopleRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return popularPeople.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}