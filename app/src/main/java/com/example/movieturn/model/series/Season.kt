package com.example.movieturn.model.series

data class Season(
    val air_date: String? = null,
    val episode_count: Int? = null,
    val id: Int,
    val name: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val season_number: Int? = null,
    val vote_average: Double? = null
)