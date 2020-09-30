package com.yelloco.movieapp.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.yelloco.movieapp.models.popular.Person

class PopularPeopleDataSourceFactory(private val peopleDataSource: PopularPeopleDataSource) :
    DataSource.Factory<Int, Person>() {
    val popularPeopleMutableLiveData = MutableLiveData<PopularPeopleDataSource>()
    override fun create(): DataSource<Int, Person> {
        popularPeopleMutableLiveData.postValue(peopleDataSource)

        return peopleDataSource
    }
}