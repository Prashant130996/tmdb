package com.example.movieturn.model.series

data class SeriesByIdRes(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val created_by: List<Any>? = null,
    val episode_run_time: List<Int> = listOf(),
    val first_air_date: String? = null,
    val genres: List<Genre> = listOf(),
    val homepage: String? = null,
    val id: Int,
    val in_production: Boolean? = null,
    val languages: List<String>,
    val last_air_date: String? = null,
    val last_episode_to_air: LastEpisodeToAir? = null,
    val name: String? = null,
    val networks: List<Network> = listOf(),
    val next_episode_to_air: NextEpisodeToAir? = null,
    val number_of_episodes: Int? = null,
    val number_of_seasons: Int? = null,
    val origin_country: List<String> = listOf(),
    val original_language: String? = null,
    val original_name: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val seasons: List<Season> = listOf(),
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String? = null,
    val tagline: String? = null,
    val type: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)