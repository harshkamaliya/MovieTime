package com.example.movie.dataModel

import java.io.Serializable

data class PageResponseDTO(
	val summary: String? = null,
	val image: ImageDTO? = null,
	val averageRuntime: Int? = null,
	val links: LinksDTO? = null,
	val premiered: String? = null,
	val rating: RatingDTO? = null,
	val runtime: Int? = null,
	val weight: Int? = null,
	val language: String? = null,
	val type: String? = null,
	val url: String? = null,
	val officialSite: String? = null,
	val network: NetworkDTO? = null,
	val schedule: ScheduleDTO? = null,
	val genres: List<String?>? = null,
	val name: String? = null,
	val id: Int? = null,
	val externals: ExternalsDTO? = null,
	val updated: Int? = null,
	val status: String? = null
):Serializable