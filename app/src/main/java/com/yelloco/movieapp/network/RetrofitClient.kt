package com.yelloco.movieapp.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val API_KEY = "5f7b809fb09508781ee9941d1a47ff07"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val PROFILE_IMAGE_URL_500 = "https://image.tmdb.org/t/p/w500"
const val ORIGINAL_PROFILE_BASE_URL = "https://image.tmdb.org/t/p/original"

object RetrofitClient {

    fun getClient(): TheMovieDBAPI {
        val interceptor = Interceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter("api_key", API_KEY).build()

            val request = chain.request().newBuilder().url(url).build()

            return@Interceptor chain.proceed(request)
        }

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(TheMovieDBAPI::class.java)
    };
}