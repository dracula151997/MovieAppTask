package com.yelloco.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.yelloco.movieapp.datasource.PopularPeopleDataSource
import com.yelloco.movieapp.datasource.PopularPeopleDataSourceFactory
import com.yelloco.movieapp.models.popular.Person
import com.yelloco.movieapp.network.NetworkingState

class PopularPeopleRepository(private val peopleDataSourceFactory: PopularPeopleDataSourceFactory) {

    lateinit var popularPeoplePagedList: LiveData<PagedList<Person>>

    fun fetchPeoplePagedList(): LiveData<PagedList<Person>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        popularPeoplePagedList = LivePagedListBuilder(peopleDataSourceFactory, config).build()

        return popularPeoplePagedList
    }

    fun getNetworkState(): LiveData<NetworkingState> {
        return Transformations.switchMap(
            peopleDataSourceFactory.popularPeopleMutableLiveData,
            PopularPeopleDataSource::networkingStateLiveData
        )
    }
}