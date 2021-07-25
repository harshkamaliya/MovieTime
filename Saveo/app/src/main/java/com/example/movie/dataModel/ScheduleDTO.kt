package com.example.movie.dataModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ScheduleDTO(

	@field:SerializedName("days")
	val days: List<String?>? = null,

	@field:SerializedName("time")
	val time: String? = null
):Serializable