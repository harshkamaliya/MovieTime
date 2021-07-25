package com.example.movie.dataModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageDTO(

	@field:SerializedName("original")
	val original: String? = null,

	@field:SerializedName("medium")
	val medium: String? = null
)