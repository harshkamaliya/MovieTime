package com.example.movie.dataModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LinksDTO(

	@field:SerializedName("self")
	val self: SelfDTO? = null,

	@field:SerializedName("previousepisode")
	val previousepisode: PreviousepisodeDTO? = null
):Serializable