package com.example.movie.dataModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SelfDTO(

	@field:SerializedName("href")
	val href: String? = null
)