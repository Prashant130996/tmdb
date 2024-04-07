package com.example.movieturn.domain.models

import com.example.movieturn.utils.MediaType

data class Series(
    val backdropPath: String,
    val firstAirDate: String,
    val genres: List<String> = listOf(),
    val id: Int,
    val isFav: Boolean,
    val mediaType: MediaType = MediaType.SERIES,
    val name: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val seasons: List<Season> = listOf(),
)

data class Season(
    val airDate: String,
    val episodeCount: Int,
    val id: Int,
    val name: String,
)