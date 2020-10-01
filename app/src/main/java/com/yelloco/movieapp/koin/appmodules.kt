package com.yelloco.movieapp.koin

import com.yelloco.movieapp.repository.PopularPeopleRepository
import com.yelloco.movieapp.datasource.PersonDetailsDataSource
import com.yelloco.movieapp.datasource.PopularPeopleDataSource
import com.yelloco.movieapp.datasource.PopularPeopleDataSourceFactory
import com.yelloco.movieapp.network.RetrofitClient
import com.yelloco.movieapp.repository.PersonDetailsRepository
import com.yelloco.movieapp.viewmodel.PersonDetailsViewModel
import com.yelloco.movieapp.viewmodel.PopularPeopleViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitClient }
    single { CompositeDisposable() }
}
val dataSources = module {
    single { PopularPeopleDataSource(get(), get()) }
    single { PopularPeopleDataSourceFactory(get()) }
    single { PersonDetailsDataSource(get(), get()) }
}

val repositories = module {
    single { PopularPeopleRepository(get()) }
    single { PersonDetailsRepository(get()) }
}

val viewModels = module {
    viewModel { PopularPeopleViewModel(get(), get()) }
    viewModel { PersonDetailsViewModel(get(), get()) }
}