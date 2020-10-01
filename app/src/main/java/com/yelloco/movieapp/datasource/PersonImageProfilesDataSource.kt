package com.yelloco.movieapp.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yelloco.movieapp.models.image.PersonImagesResponse
import com.yelloco.movieapp.network.NetworkingState
import com.yelloco.movieapp.network.RetrofitClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PersonImageProfilesDataSource(
    private val apiService: RetrofitClient,
    private val compositeDisposable: CompositeDisposable
) {

    private val profileImagesMutableLiveData = MutableLiveData<PersonImagesResponse>()

    val profileImagesLiveData: LiveData<PersonImagesResponse>
        get() = profileImagesMutableLiveData

    private val networkingStateMutableLiveData = MutableLiveData<NetworkingState>()

    val networkStateLiveData: LiveData<NetworkingState>
        get() = networkingStateMutableLiveData

    fun fetchProfileImages(personId: Int) {
        networkingStateMutableLiveData.postValue(NetworkingState.LOADING)
        compositeDisposable.add(
            apiService.getClient()
                .getPersonImages(personId)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    networkingStateMutableLiveData.postValue(NetworkingState.LOADED)
                    profileImagesMutableLiveData.postValue(it)
                }, {
                    networkingStateMutableLiveData.postValue(NetworkingState.ERROR)
                })
        )
    }

}