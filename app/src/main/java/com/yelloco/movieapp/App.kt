package com.yelloco.movieapp

import android.app.Application
import com.yelloco.movieapp.koin.appModule
import com.yelloco.movieapp.koin.dataSources
import com.yelloco.movieapp.koin.repositories
import com.yelloco.movieapp.koin.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repositories, viewModels, dataSources, appModule))
        }
    }
}