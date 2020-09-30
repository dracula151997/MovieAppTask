package com.yelloco.movieapp.koin

import com.yelloco.movieapp.PopularPeopleRepository
import com.yelloco.movieapp.datasource.PopularPeopleDataSource
import com.yelloco.movieapp.datasource.PopularPeopleDataSourceFactory
import com.yelloco.movieapp.network.RetrofitClient
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
}

val repositories = module {
    single { PopularPeopleRepository(get()) }
}

val viewModels = module {
    viewModel { PopularPeopleViewModel(get(), get()) }
}