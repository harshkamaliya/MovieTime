package com.example.movie.dataModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountryDTO(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)