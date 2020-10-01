package com.yelloco.movieapp.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.yelloco.movieapp.models.popular.Person
import com.yelloco.movieapp.network.NetworkingState
import com.yelloco.movieapp.network.RetrofitClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PopularPeopleDataSource(
    private val apiService: RetrofitClient,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Person>() {
    private var page = 1
    private val networkStateMutableLiveData = MutableLiveData<NetworkingState>()

    val networkingStateLiveData: LiveData<NetworkingState>
        get() = networkStateMutableLiveData

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Person>
    ) {
        networkStateMutableLiveData.postValue(NetworkingState.LOADING)
        compositeDisposable.add(
            apiService.getClient().getPopularPeople(page)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callback.onResult(it.results, null, page + 1)
                    networkStateMutableLiveData.postValue(NetworkingState.LOADED)
                }, {
                    networkStateMutableLiveData.postValue(NetworkingState.ERROR)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Person>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Person>) {
        networkStateMutableLiveData.postValue(NetworkingState.LOADING)
        compositeDisposable.add(
            apiService.getClient().getPopularPeople(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (it.totalPages >= params.key) {
                        callback.onResult(it.results, params.key + 1)
                        networkStateMutableLiveData.postValue(NetworkingState.LOADED)
                    } else {
                        networkStateMutableLiveData.postValue(NetworkingState.LOADED)
                    }

                }, {
                    networkStateMutableLiveData.postValue(NetworkingState.ERROR)
                })
        )
    }

}