package com.example.movie.dataModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NetworkDTO(

	@field:SerializedName("country")
	val country: CountryDTO? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
):Serializable