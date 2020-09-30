package com.yelloco.movieapp.models.image

import com.google.gson.annotations.SerializedName

data class PersonImagesResponse(

	@field:SerializedName("profiles")
	val profiles: List<ProfilesItem>,

	@field:SerializedName("id")
	val id: Int
)

data class ProfilesItem(

	@field:SerializedName("file_path")
	val filePath: String,

	@field:SerializedName("aspect_ratio")
	val aspectRatio: Double,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("iso_639_1")
	val iso6391: Any,

	@field:SerializedName("vote_count")
	val voteCount: Int,

	@field:SerializedName("height")
	val height: Int
)
