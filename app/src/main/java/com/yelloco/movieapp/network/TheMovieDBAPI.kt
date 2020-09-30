package com.yelloco.movieapp.network

import com.yelloco.movieapp.models.details.PersonDetailsResponse
import com.yelloco.movieapp.models.image.PersonImagesResponse
import com.yelloco.movieapp.models.popular.PopularPeopleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBAPI {
    @GET("person/popular")
    fun getPopularPeople(@Query("page") page: Int): Single<PopularPeopleResponse>

    @GET("person/{person_id}")
    fun getPersonDetails(@Path("person_id") personId: Int): Single<PersonDetailsResponse>

    @GET("person/{person_id}/images")
    fun getPersonImages(@Path("person_id") personId: Int): Single<PersonImagesResponse>


}