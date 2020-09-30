package com.yelloco.movieapp.models.details

import com.google.gson.annotations.SerializedName

data class PersonDetailsResponse(

	@field:SerializedName("birthday")
	val birthday: String,

	@field:SerializedName("also_known_as")
	val alsoKnownAs: List<String>,

	@field:SerializedName("gender")
	val gender: Int,

	@field:SerializedName("imdb_id")
	val imdbId: String,

	@field:SerializedName("known_for_department")
	val knownForDepartment: String,

	@field:SerializedName("profile_path")
	val profilePath: String,

	@field:SerializedName("biography")
	val biography: String,

	@field:SerializedName("deathday")
	val deathday: Any,

	@field:SerializedName("place_of_birth")
	val placeOfBirth: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("adult")
	val adult: Boolean,

	@field:SerializedName("homepage")
	val homepage: Any
)
