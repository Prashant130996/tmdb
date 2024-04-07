package com.example.movieturn.model.search

data class Result(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val first_air_date: String? = null,
    val gender: Int,
    val genre_ids: List<Int> = listOf(),
    val id: Int,
    val known_for: List<KnownFor> = listOf(),
    val known_for_department: String? = null,
    val media_type: String? = null,
    val name: String? = null,
    val origin_country: List<String> = listOf(),
    val original_language: String? = null,
    val original_name: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val profile_path: Any? = null,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)