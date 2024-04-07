package com.example.movieturn.model.movie

data class MovieByIdRes(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any? = null,
    val budget: Int? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    val id: Int,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int? = null
)