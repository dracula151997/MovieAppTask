package com.yelloco.movieapp.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yelloco.movieapp.models.details.PersonDetailsResponse
import com.yelloco.movieapp.network.NetworkingState
import com.yelloco.movieapp.network.RetrofitClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PersonDetailsDataSource(
    val apiService: RetrofitClient,
    val compositeDisposable: CompositeDisposable
) {

    private val personDetailsMutableLiveData = MutableLiveData<PersonDetailsResponse>()

    val personDetailsLiveData: LiveData<PersonDetailsResponse>
        get() = personDetailsMutableLiveData

    private val networkStateMutableLiveData = MutableLiveData<NetworkingState>()

    val networkStateLiveData: LiveData<NetworkingState>
        get() = networkStateMutableLiveData


    fun fetchPersonDetails(personId: Int) {
        networkStateMutableLiveData.postValue(NetworkingState.LOADING)
        compositeDisposable.add(
            apiService.getClient().getPersonDetails(personId)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    networkStateMutableLiveData.postValue(NetworkingState.LOADED)
                    personDetailsMutableLiveData.postValue(it)
                }, {
                    Log.d("TAG", "fetchPersonDetails: Something went error ${it}")
                    networkStateMutableLiveData.postValue(NetworkingState.ERROR)
                })
        )
    }
}