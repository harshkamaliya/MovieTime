package com.example.movie.dataModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RatingDTO(

	@field:SerializedName("average")
	val average: Any? = null
)