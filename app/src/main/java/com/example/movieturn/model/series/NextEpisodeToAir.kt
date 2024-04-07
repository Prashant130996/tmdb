package com.example.movieturn.model.series

data class NextEpisodeToAir(
    val air_date: String? = null,
    val episode_number: Int? = null,
    val episode_type: String? = null,
    val id: Int,
    val name: String? = null,
    val overview: String? = null,
    val production_code: String? = null,
    val runtime: Int? = null,
    val season_number: Int? = null,
    val show_id: Int? = null,
    val still_path: Any? = null,
    val vote_average: Int? = null,
    val vote_count: Int? = null
)